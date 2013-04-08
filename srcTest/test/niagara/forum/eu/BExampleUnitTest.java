// Copyright (c) 2013
// Licensed under the Academic Free License 3.0

package test.niagara.forum.eu;

import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tridium.testng.BTestNg;

/**
 * @author: Matthew Giannini
 */
public class BExampleUnitTest extends BTestNg
{
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BExampleUnitTest.class);

  public BExampleUnitTest()
  {
  }

  @Test
  public void one_plus_one_equals_two()
  {
    Assert.assertEquals((1 + 1), 2, "java add is broken?");
  }
  // TODO: test using station
}
