// Copyright (c) 2013
// Licensed under the Academic Free License 3.0

package test.niagara.forum.eu.bql;

import javax.baja.bql.BqlQuery;
import javax.baja.collection.BICollection;
import javax.baja.collection.BITable;
import javax.baja.collection.Column;
import javax.baja.naming.OrdTarget;
import javax.baja.sys.BInteger;
import javax.baja.sys.BLong;
import javax.baja.sys.BObject;
import javax.baja.sys.BSimple;
import javax.baja.sys.BString;
import javax.baja.sys.Flags;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;
import javax.baja.util.BFolder;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.tridium.testng.BTestNg;

/**
 * @author: Matthew Giannini
 */
public class BBqlTest extends BTestNg
{
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BBqlTest.class);

  BFolder root;

  @BeforeClass
  public void initTest()
  {
    // create a subfolder with an integer
    BFolder subFolder = new BFolder();
    subFolder.add("integer", BInteger.make(100));

    // create the root folder and children
    // root
    //   - string (NiagaraForum) (readonly)
    //   + subfolder
    //     - integer (100)
    //   + zed
    //   + alpha
    //
    root = new BFolder();
    root.add("string", BString.make("NiagaraForum"), Flags.READONLY);
    root.add("subfolder", subFolder);
    root.add("zed", new BFolder());
    root.add("alpha", new BFolder());
  }

  @Test
  public void test_expression_query()
  {
    // You can calculate one arbitrary expression and get the result back as a BObject
    // instead of a BICollection.

    BSimple result = query("subfolder.integer / 2", root).asSimple();
    Assert.assertEquals(result, BLong.make(50));
  }

  @Test
  public void test_select_query()
  {
    // normal select query against unmounted root
    BITable result = tableQuery("SELECT displayName FROM baja:Folder ORDER BY 1 DESC", root);
    verifyTable(result, new String[][]{{"zed"},{"subfolder"}, {"alpha"}});
  }

  @Test
  public void test_base_expression_query()
  {
    // test base expression query. Very similar to expression query, except that
    // you can specify multiple expression to evaluate against the base.
    //
    // you get back a table with exactly one row.

    BITable result = tableQuery("{string, subfolder.integer * 2, (7*6) + ' is the answer'}", root);
    verifyTable(result, new String[][]{{"NiagaraForum", "200", "42 is the answer"}});
  }

  @Test
  public void test_scalar_readonly_function()
  {
    BITable result = tableQuery("{nfeu:Lib.readonly('string'), nfeu:Lib.readonly('subfolder')}", root);
    verifyTable(result, new String[][]{{"true", "false"}});
  }

  /**
   * Get the result of the query as a table
   */
  protected BITable tableQuery(String bql, BObject o)
  {
    // Must treat as BICollection and then convert to table!
    return ((BICollection)query(bql, o)).toTable();
  }

  protected BObject query(String bql, BObject o)
  {
    // make an unmounted ord target for the base of the query
    OrdTarget unmountedBase = OrdTarget.unmounted(o);

    // return the result of the query
    return BqlQuery.make(bql).resolve(unmountedBase).get();
  }

  protected void verifyTable(BITable table, String[][] values)
  {
    int size = table.size();
    if (size != values.length)
    {
      fail("table size does not match expected size. Actual: " + size  + " != Expected: " + values.length);
    }
    Column[] columns = table.getColumns().list();

    for(int r = 0; r < size; ++r)
    {
      for(int c = 0; c < columns.length; ++c)
      {
        String actual = table.getString(r, columns[c]);
        String expected = values[r][c];
        if (!actual.equals(expected))
        {
          fail("("+r+","+c+") got " + actual + ", expected: " + expected);
        }
      }
    }
    Assert.assertTrue(true);
  }
}
