// Copyright (c) 2013
// Licensed under the Academic Free License 3.0

package test.niagara.forum.eu.async;

import javax.baja.sys.BRelTime;
import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import niagara.forum.eu.async.BThreadPool;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tridium.testng.BTestNg;
import com.tridium.util.backport.concurrent.CountDownLatch;
import com.tridium.util.backport.concurrent.Future;
import com.tridium.util.backport.concurrent.TimeUnit;

/**
 * @author: Matthew Giannini
 */
public class BThreadPoolTest extends BTestNg
{
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BThreadPoolTest.class);

  private BThreadPool pool;

  @BeforeTest
  public void initTest()
  {
    pool = new BThreadPool();
  }

  @AfterTest
  public void cleanupTest() throws Exception
  {
    // this is very important or thread executors leak
    pool.stopped();
  }

  @Test
  public void verify_default_max_pool_size()
  {
    Assert.assertEquals(pool.getMaximumPoolSize(), 4);
  }

  @Test
  public void verify_default_keep_alive()
  {
    Assert.assertEquals(pool.getKeepAliveTime(), BRelTime.makeSeconds(60));
  }

  @Test
  public void test_execute_task() throws Exception
  {
    final int num = 10;
    final CountDownLatch latch = new CountDownLatch(num);
    for (int i=0; i<num; ++i)
      pool.execute(new Runnable() {
        public void run() {
          latch.countDown();
        }
      });
    Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
  }

  @Test
  public void test_submit_task() throws Exception
  {
    final StringBuilder sb = new StringBuilder();
    Future f = pool.submit(new Runnable() {
      public void run()
      {
        sb.append("success");
      }
    });
    Assert.assertNull(f.get(5, TimeUnit.SECONDS));
    Assert.assertEquals(sb.toString(), "success");
  }
}
