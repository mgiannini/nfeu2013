// Copyright (c) 2013
// Licensed under the Academic Free License 3.0

package niagara.forum.eu.bql;

import javax.baja.sys.BBoolean;
import javax.baja.sys.BComplex;
import javax.baja.sys.BObject;
import javax.baja.sys.BString;
import javax.baja.sys.Flags;
import javax.baja.sys.Slot;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

/**
 * @author: Matthew Giannini
 */
public class BLib extends BObject
{
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BLib.class);

  /**
   * Return true if the target is a BComplex and it has a property slot with the given
   * name that has the readonly flag set.
   */
  public static BBoolean readonly(BObject target, BString slotName)
  {
    if (target == null) return BBoolean.FALSE;
    if (!target.isComplex()) return BBoolean.FALSE;

    BComplex complex = target.asComplex();
    String name = slotName.getString();
    Slot slot = complex.getSlot(name);
    return BBoolean.make(slot != null && slot.isProperty() && Flags.isReadonly(complex, slot));
  }

  // Aggregate functions
  //
  public static final Type[] cycles = new Type[] {BCyclesAggregate.TYPE};
}
