#!/bin/sh

FILE_TO_UPLOAD_NAME=advertisingdata_to_upload.log
cd /home/pi/blegateway







#Wait for an active Internet connection before logging bluetooth to make sure the time was syncronized with the internet


	echo "*** Test Internet Connection"
	while true
	do
		wget --spider http://www.google.com --quiet
		if [ "$?" != 0 ];	 
			then 
				echo "***!!! No internet"
				sudo ifdown wlan0
				sudo ifup wlan0
			else
			#internet connection is active and time should be synced
			echo "*** Internet Connection good"
			break
		fi
	echo "*** Wait for Internet..."
	sleep 60
	done





echo "*** Start scanning for Bluetooth Low Energy devices..."
hcitool lescan > /dev/null &
PID_LESCAN=$!

echo "*** Dump raw advertising Data into temporary file..."
#In raw format with timestamp and in hex format
hcidump --raw -t -x >> advertisingdata.log &			
PID_HCIDUMP=$!


# Loop
while true;
do
	

	#Prepare Gateway Info
		echo "GatewayTimestamp" > gatewayinfo.log
		date +%s >> gatewayinfo.log
		echo "GatewayCpuInfo" >> gatewayinfo.log
		cat /proc/cpuinfo >> gatewayinfo.log

	#Prepare file to upload. If there are not completed updates, back up before
		cp --backup=numbered advertisingdata.log $FILE_TO_UPLOAD_NAME
	#TODO: The Log File is cleared even if the Logs where not uploaded! Workaround to avoid big file sizes not able to upload on weak connections
		echo "*** Clear Log File..."
		echo "" > advertisingdata.log


	# Test Internet Connection
	echo "*** Test Internet Connection"
	wget --spider http://www.google.com --quiet
	if [ "$?" != 0 ];	 
		then 
			echo "***!!! No internet"
			sudo ifdown wlan0
			sudo ifup wlan0
		else		
			# Internet Connection available, upload to server
			
						

			#Upload all log files that were not uploaded yet
			FILE_TO_UPLOAD_NAME_NOW=$FILE_TO_UPLOAD_NAME
			IFORNAME=0
			while [ $IFORNAME -le 5000 ]
			do
				if [  -f $FILE_TO_UPLOAD_NAME_NOW ]
				then
						echo "*** Upload to server file: $FILE_TO_UPLOAD_NAME_NOW"

					# Try to upload to server. If somethings fails try again
						IFOR=0
						while [ $IFOR -le 10 ]
						do
							echo "******Try $IFOR" 
							curl -F "advertisingdata=@$FILE_TO_UPLOAD_NAME_NOW" -F "gatewayinfo=@gatewayinfo.log" http://www.starnberger.at/ant/uploadGateway.php | grep "OK"
							if [ $? -eq 0 ]; 
								then
								#Upload was successful! Exit retry Looop
									IFOR=12
									rm $FILE_TO_UPLOAD_NAME_NOW
								else
								#Somethings went wrong with the Upload. Retry!
									IFOR=$(( $IFOR + 1 ))
									echo "***!!! Server not reachable"
									sleep 3
							fi
						done
				fi

				IFORNAME=$(( $IFORNAME + 1 ))
				FILE_TO_UPLOAD_NAME_NOW="$FILE_TO_UPLOAD_NAME.~$IFORNAME~"
			done

	fi
		
	
	
	# Check if the background scanning and dumping to file are still running, if not, its better to reboot system to restart all services
	echo "***** Check if Background scanning is still active"

	if ! kill -0 $PID_LESCAN || ! kill -0 $PID_HCIDUMP;
		then
		#TODO: This is a Workaround. Here it would be better to restart only the trouble making services
			echo "***** ERROR: Background Processes are not running anymore!!! Better restart system"
			reboot -n
	fi
		

	# Wait before uploading to server the next chunk
	echo "***** wait for next upload"
	sleep 900

	



	
done