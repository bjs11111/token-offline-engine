package com.starnberger.tokenofflineengine.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
public class SensorData implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private String id;
   @Version
   @Column(name = "version")
   private int version;

   @Column
   @Temporal(TemporalType.DATE)
   private Date timestamp;

   @Column
   private SensorType sensorType;

   @Column
   private Gateway gateway;

   @Column
   private double value1;

   @Column
   private double value2;

   @Column
   private double value3;

   @Column
   private Token token;

   public String getId()
   {
      return this.id;
   }

   public void setId(final String id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof SensorData))
      {
         return false;
      }
      SensorData other = (SensorData) obj;
      if (id != null)
      {
         if (!id.equals(other.id))
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
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public Date getTimestamp()
   {
      return timestamp;
   }

   public void setTimestamp(Date timestamp)
   {
      this.timestamp = timestamp;
   }

   public SensorType getSensorType()
   {
      return sensorType;
   }

   public void setSensorType(SensorType sensorType)
   {
      this.sensorType = sensorType;
   }

   public Gateway getGateway()
   {
      return gateway;
   }

   public void setGateway(Gateway gateway)
   {
      this.gateway = gateway;
   }

   public double getValue1()
   {
      return value1;
   }

   public void setValue1(double value1)
   {
      this.value1 = value1;
   }

   public double getValue2()
   {
      return value2;
   }

   public void setValue2(double value2)
   {
      this.value2 = value2;
   }

   public double getValue3()
   {
      return value3;
   }

   public void setValue3(double value3)
   {
      this.value3 = value3;
   }

   public Token getToken()
   {
      return token;
   }

   public void setToken(Token token)
   {
      this.token = token;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (id != null)
         result += "id: " + id;
      result += ", version: " + version;
      if (timestamp != null)
         result += ", timestamp: " + timestamp;
      if (sensorType != null)
         result += ", sensorType: " + sensorType;
      if (gateway != null)
         result += ", gateway: " + gateway;
      result += ", value1: " + value1;
      result += ", value2: " + value2;
      result += ", value3: " + value3;
      if (token != null)
         result += ", token: " + token;
      return result;
   }
}