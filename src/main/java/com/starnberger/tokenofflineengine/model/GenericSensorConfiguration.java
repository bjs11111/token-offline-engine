package com.starnberger.tokenofflineengine.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "GEN")
public class GenericSensorConfiguration extends SensorConfiguration
{

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof GenericSensorConfiguration))
      {
         return false;
      }
      GenericSensorConfiguration other = (GenericSensorConfiguration) obj;
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
}