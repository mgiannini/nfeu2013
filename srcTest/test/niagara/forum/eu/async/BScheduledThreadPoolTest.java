// Copyright (c) 2013
// Licensed under the Academic Free License 3.0

package test.niagara.forum.eu.async;

import javax.baja.sys.Sys;
import javax.baja.sys.Type;

import niagara.forum.eu.async.BScheduledThreadPool;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.tridium.testng.BTestNg;
import com.tridium.util.backport.concurrent.CountDownLatch;
import com.tridium.util.backport.concurrent.ScheduledFuture;
import com.tridium.util.backport.concurrent.TimeUnit;

/**
 * @author: Matthew Giannini
 */
public class BScheduledThreadPoolTest extends BTestNg
{
  public Type getType() { return TYPE; }
  public static final Type TYPE = Sys.loadType(BScheduledThreadPoolTest.class);

  private BScheduledThreadPool pool;

  @BeforeTest
  public void initTest()
  {
    pool = new BScheduledThreadPool();
  }

  @AfterTest
  public void cleanupTest() throws Exception
  {
    pool.stopped();
  }

  @Test
  public void verify_default_core_pool_size()
  {
    Assert.assertEquals(pool.getCorePoolSize(), 4);
  }

  @Test
  public void test_delay_schedule() throws Exception
  {
    final CountDownLatch latch = new CountDownLatch(1);
    ScheduledFuture f = pool.schedule(new Runnable() {
      public void run() { latch.countDown(); }
    }, 2, TimeUnit.SECONDS);
    Assert.assertFalse(f.isDone());
    Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    Assert.assertTrue(f.isDone());
  }

  @Test
  public void test_fixed_rate_schedule() throws Exception
  {
    final int iterations = 4;
    final CountDownLatch latch = new CountDownLatch(iterations);
    ScheduledFuture f = pool.scheduleAtFixedRate(new Runnable() {
      public void run() { latch.countDown(); }
    }, 0, 1, TimeUnit.SECONDS);
    Assert.assertTrue(latch.await(iterations + 2, TimeUnit.SECONDS));
    f.cancel(true);
    Assert.assertTrue(f.isCancelled());
  }

}
