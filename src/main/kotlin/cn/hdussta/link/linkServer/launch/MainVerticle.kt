package cn.hdussta.link.linkServer.launch

import cn.hdussta.link.linkServer.data.DataStorageVerticle
import cn.hdussta.link.linkServer.manager.ManagerVerticle
import cn.hdussta.link.linkServer.transport.http.HTTPVerticle
import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.kotlin.coroutines.awaitResult
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @name MainVerticle
 * @description 入口Verticle
 * @author Wooyme
 */
class MainVerticle : AbstractVerticle() {

  override fun start(startFuture: Future<Void>) {
    GlobalScope.launch(vertx.dispatcher()) {
      val managerVerticleId = awaitResult<String> {
        vertx.deployVerticle(ManagerVerticle(), it)
      }
      println(managerVerticleId)
      val dataStorageVerticleId = awaitResult<String> {
        vertx.deployVerticle(DataStorageVerticle(), it)
      }
      println(dataStorageVerticleId)
      val httpVerticleId = awaitResult<String> {
        vertx.deployVerticle(HTTPVerticle(), it)
      }
      println(httpVerticleId)
      startFuture.complete()
    }
  }
}
