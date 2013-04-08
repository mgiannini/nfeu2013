// Copyright (c) 2013
// Licensed under the Academic Free License 3.0

package niagara.forum.eu.bql;

import javax.baja.bql.BIAggregator;
import javax.baja.sys.BBoolean;
import javax.baja.sys.BLong;
import javax.baja.sys.BObject;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

/**
 * @author: Matthew Giannini
 * @author: James Johnson
 */
public class BCyclesAggregate extends BObject implements BIAggregator
{
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BCyclesAggregate.class);

  private boolean lastValue;
  private long cycles = -1;

  public BCyclesAggregate()
  {
  }

  public void aggregate(BBoolean value)
  {
    aggregate(value.getBoolean());
  }

  public void aggregate(boolean value)
  {
    if (lastValue != value || cycles < 0) ++cycles;
    lastValue = value;
  }

  public BLong commit()
  {
    return BLong.make(cycles);
  }
}

