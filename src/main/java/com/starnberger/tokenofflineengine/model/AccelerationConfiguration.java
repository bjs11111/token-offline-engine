package com.starnberger.tokenofflineengine.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "ACC")
public class AccelerationConfiguration extends SensorConfiguration
{

   @Column
   private int bleAdvertisingConditionOnMotion;

   @Column
   private int bleAdvertisingConditionOrientationAlarm;

   @Column
   private int bleAdvertisingConditionMechanicalShock;

   @Column
   private int bleNotAdvertisingConditionOnMotion;

   @Column
   private int bleNotAdvertisingConditionOrientationAlarm;

   @Column
   private double sensorOrientationAlarmHighX;

   @Column
   private double sensorOrientationAlarmHighY;

   @Column
   private double sensorOrientationAlarmHighZ;

   @Column
   private double sensorOrientationAlarmLowX;

   @Column
   private double sensorOrientationAlarmLowY;

   @Column
   private double sensorOrientationAlarmLowZ;

   @Column
   private double sensorMechanicalShockAlarmG;

   @Column
   private double sensorMechanicalShockSemplingFrequency;

   @Column
   private double sensorMechanicalShockCutOffLowPassFrequency;

   @Column
   private double sensorMechanicalShockCutOffHighPassFrequency;

   @Column
   private boolean sensorMechanicalShockEnabled;

   @Column
   private boolean sensorMechanicalShockBroadcastEnabled;


   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof AccelerationConfiguration))
      {
         return false;
      }
      AccelerationConfiguration other = (AccelerationConfiguration) obj;
      if (getId()!= null)
      {
         if (!getId().equals(other.getId()))
         {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((getId()== null) ? 0 : getId().hashCode());
      return result;
   }

   public int getBleAdvertisingConditionOnMotion()
   {
      return bleAdvertisingConditionOnMotion;
   }

   public void setBleAdvertisingConditionOnMotion(
         int bleAdvertisingConditionOnMotion)
   {
      this.bleAdvertisingConditionOnMotion = bleAdvertisingConditionOnMotion;
   }

   public int getBleAdvertisingConditionOrientationAlarm()
   {
      return bleAdvertisingConditionOrientationAlarm;
   }

   public void setBleAdvertisingConditionOrientationAlarm(
         int bleAdvertisingConditionOrientationAlarm)
   {
      this.bleAdvertisingConditionOrientationAlarm = bleAdvertisingConditionOrientationAlarm;
   }

   public int getBleAdvertisingConditionMechanicalShock()
   {
      return bleAdvertisingConditionMechanicalShock;
   }

   public void setBleAdvertisingConditionMechanicalShock(
         int bleAdvertisingConditionMechanicalShock)
   {
      this.bleAdvertisingConditionMechanicalShock = bleAdvertisingConditionMechanicalShock;
   }

   public int getBleNotAdvertisingConditionOnMotion()
   {
      return bleNotAdvertisingConditionOnMotion;
   }

   public void setBleNotAdvertisingConditionOnMotion(
         int bleNotAdvertisingConditionOnMotion)
   {
      this.bleNotAdvertisingConditionOnMotion = bleNotAdvertisingConditionOnMotion;
   }

   public int getBleNotAdvertisingConditionOrientationAlarm()
   {
      return bleNotAdvertisingConditionOrientationAlarm;
   }

   public void setBleNotAdvertisingConditionOrientationAlarm(
         int bleNotAdvertisingConditionOrientationAlarm)
   {
      this.bleNotAdvertisingConditionOrientationAlarm = bleNotAdvertisingConditionOrientationAlarm;
   }

   public double getSensorOrientationAlarmHighX()
   {
      return sensorOrientationAlarmHighX;
   }

   public void setSensorOrientationAlarmHighX(double sensorOrientationAlarmHighX)
   {
      this.sensorOrientationAlarmHighX = sensorOrientationAlarmHighX;
   }

   public double getSensorOrientationAlarmHighY()
   {
      return sensorOrientationAlarmHighY;
   }

   public void setSensorOrientationAlarmHighY(double sensorOrientationAlarmHighY)
   {
      this.sensorOrientationAlarmHighY = sensorOrientationAlarmHighY;
   }

   public double getSensorOrientationAlarmHighZ()
   {
      return sensorOrientationAlarmHighZ;
   }

   public void setSensorOrientationAlarmHighZ(double sensorOrientationAlarmHighZ)
   {
      this.sensorOrientationAlarmHighZ = sensorOrientationAlarmHighZ;
   }

   public double getSensorOrientationAlarmLowX()
   {
      return sensorOrientationAlarmLowX;
   }

   public void setSensorOrientationAlarmLowX(double sensorOrientationAlarmLowX)
   {
      this.sensorOrientationAlarmLowX = sensorOrientationAlarmLowX;
   }

   public double getSensorOrientationAlarmLowY()
   {
      return sensorOrientationAlarmLowY;
   }

   public void setSensorOrientationAlarmLowY(double sensorOrientationAlarmLowY)
   {
      this.sensorOrientationAlarmLowY = sensorOrientationAlarmLowY;
   }

   public double getSensorOrientationAlarmLowZ()
   {
      return sensorOrientationAlarmLowZ;
   }

   public void setSensorOrientationAlarmLowZ(double sensorOrientationAlarmLowZ)
   {
      this.sensorOrientationAlarmLowZ = sensorOrientationAlarmLowZ;
   }

   public double getSensorMechanicalShockAlarmG()
   {
      return sensorMechanicalShockAlarmG;
   }

   public void setSensorMechanicalShockAlarmG(double sensorMechanicalShockAlarmG)
   {
      this.sensorMechanicalShockAlarmG = sensorMechanicalShockAlarmG;
   }

   public double getSensorMechanicalShockSemplingFrequency()
   {
      return sensorMechanicalShockSemplingFrequency;
   }

   public void setSensorMechanicalShockSemplingFrequency(
         double sensorMechanicalShockSemplingFrequency)
   {
      this.sensorMechanicalShockSemplingFrequency = sensorMechanicalShockSemplingFrequency;
   }

   public double getSensorMechanicalShockCutOffLowPassFrequency()
   {
      return sensorMechanicalShockCutOffLowPassFrequency;
   }

   public void setSensorMechanicalShockCutOffLowPassFrequency(
         double sensorMechanicalShockCutOffLowPassFrequency)
   {
      this.sensorMechanicalShockCutOffLowPassFrequency = sensorMechanicalShockCutOffLowPassFrequency;
   }

   public double getSensorMechanicalShockCutOffHighPassFrequency()
   {
      return sensorMechanicalShockCutOffHighPassFrequency;
   }

   public void setSensorMechanicalShockCutOffHighPassFrequency(
         double sensorMechanicalShockCutOffHighPassFrequency)
   {
      this.sensorMechanicalShockCutOffHighPassFrequency = sensorMechanicalShockCutOffHighPassFrequency;
   }

   public boolean isSensorMechanicalShockEnabled()
   {
      return sensorMechanicalShockEnabled;
   }

   public void setSensorMechanicalShockEnabled(boolean sensorMechanicalShockEnabled)
   {
      this.sensorMechanicalShockEnabled = sensorMechanicalShockEnabled;
   }

   public boolean isSensorMechanicalShockBroadcastEnabled()
   {
      return sensorMechanicalShockBroadcastEnabled;
   }

   public void setSensorMechanicalShockBroadcastEnabled(
         boolean sensorMechanicalShockBroadcastEnabled)
   {
      this.sensorMechanicalShockBroadcastEnabled = sensorMechanicalShockBroadcastEnabled;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (getId()!= null)
         result += "id: " + getId();
      result += ", version: " + getVersion();
      result += ", bleAdvertisingConditionOnMotion: "
            + bleAdvertisingConditionOnMotion;
      result += ", bleAdvertisingConditionOrientationAlarm: "
            + bleAdvertisingConditionOrientationAlarm;
      result += ", bleAdvertisingConditionMechanicalShock: "
            + bleAdvertisingConditionMechanicalShock;
      result += ", bleNotAdvertisingConditionOnMotion: "
            + bleNotAdvertisingConditionOnMotion;
      result += ", bleNotAdvertisingConditionOrientationAlarm: "
            + bleNotAdvertisingConditionOrientationAlarm;
      result += ", sensorOrientationAlarmHighX: " + sensorOrientationAlarmHighX;
      result += ", sensorOrientationAlarmHighY: " + sensorOrientationAlarmHighY;
      result += ", sensorOrientationAlarmHighZ: " + sensorOrientationAlarmHighZ;
      result += ", sensorOrientationAlarmLowX: " + sensorOrientationAlarmLowX;
      result += ", sensorOrientationAlarmLowY: " + sensorOrientationAlarmLowY;
      result += ", sensorOrientationAlarmLowZ: " + sensorOrientationAlarmLowZ;
      result += ", sensorMechanicalShockAlarmG: " + sensorMechanicalShockAlarmG;
      result += ", sensorMechanicalShockSemplingFrequency: "
            + sensorMechanicalShockSemplingFrequency;
      result += ", sensorMechanicalShockCutOffLowPassFrequency: "
            + sensorMechanicalShockCutOffLowPassFrequency;
      result += ", sensorMechanicalShockCutOffHighPassFrequency: "
            + sensorMechanicalShockCutOffHighPassFrequency;
      result += ", sensorMechanicalShockEnabled: " + sensorMechanicalShockEnabled;
      result += ", sensorMechanicalShockBroadcastEnabled: "
            + sensorMechanicalShockBroadcastEnabled;
      return result;
   }
}