package com.starnberger.tokenofflineengine.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "TEM")
public class TemperatureConfiguration extends SensorConfiguration
{

   @Column
   private int bleAdvertisingConditionTemperatureAlarm;

   @Column
   private int bleNotAdvertisingConditionTemperatureAlarm;

   @Column
   private double sensorTemperatureAlarmHigh;

   @Column
   private double sensorTemperatureAlarmLow;

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof TemperatureConfiguration))
      {
         return false;
      }
      TemperatureConfiguration other = (TemperatureConfiguration) obj;
      if (getId() != null)
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
      result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
      return result;
   }

   public int getBleAdvertisingConditionTemperatureAlarm()
   {
      return bleAdvertisingConditionTemperatureAlarm;
   }

   public void setBleAdvertisingConditionTemperatureAlarm(
         int bleAdvertisingConditionTemperatureAlarm)
   {
      this.bleAdvertisingConditionTemperatureAlarm = bleAdvertisingConditionTemperatureAlarm;
   }

   public int getBleNotAdvertisingConditionTemperatureAlarm()
   {
      return bleNotAdvertisingConditionTemperatureAlarm;
   }

   public void setBleNotAdvertisingConditionTemperatureAlarm(
         int bleNotAdvertisingConditionTemperatureAlarm)
   {
      this.bleNotAdvertisingConditionTemperatureAlarm = bleNotAdvertisingConditionTemperatureAlarm;
   }

   public double getSensorTemperatureAlarmHigh()
   {
      return sensorTemperatureAlarmHigh;
   }

   public void setSensorTemperatureAlarmHigh(double sensorTemperatureAlarmHigh)
   {
      this.sensorTemperatureAlarmHigh = sensorTemperatureAlarmHigh;
   }

   public double getSensorTemperatureAlarmLow()
   {
      return sensorTemperatureAlarmLow;
   }

   public void setSensorTemperatureAlarmLow(double sensorTemperatureAlarmLow)
   {
      this.sensorTemperatureAlarmLow = sensorTemperatureAlarmLow;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (getId() != null)
         result += "id: " + getId();
      result += ", version: " + getVersion();
      result += ", bleAdvertisingConditionTemperatureAlarm: "
            + bleAdvertisingConditionTemperatureAlarm;
      result += ", bleNotAdvertisingConditionTemperatureAlarm: "
            + bleNotAdvertisingConditionTemperatureAlarm;
      result += ", sensorTemperatureAlarmHigh: " + sensorTemperatureAlarmHigh;
      result += ", sensorTemperatureAlarmLow: " + sensorTemperatureAlarmLow;
      return result;
   }
}