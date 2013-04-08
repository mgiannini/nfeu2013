// Copyright (c) 2013
// Licensed under the Academic Free License 3.0

package niagara.forum.eu.validator;

import javax.baja.naming.SlotPath;
import javax.baja.sys.BObject;
import javax.baja.sys.Context;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.BIValidator;
import javax.baja.util.CannotValidateException;
import javax.baja.util.Lexicon;

/**
 * @author: Matthew Giannini
 */
public class BSlotNameValidator extends BObject implements BIValidator
{
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BSlotNameValidator.class);

  private static final Lexicon lex = Lexicon.make(BSlotNameValidator.class);

  public void validate(BObject value, Context cx) throws CannotValidateException
  {
    final String slotName = value.toString();
    if (!SlotPath.isValidName(slotName))
      throw new CannotValidateException(lex.getText("slotName.fail", new Object[]{slotName}));
  }
}
