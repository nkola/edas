package com.nkola.edas

import java.util

import com.nkola.edas.providers.PassThroughKmsProvider
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterEach, FunSuite}

@RunWith(classOf[JUnitRunner])
class DefaultEdasProviderManagerTest extends FunSuite with BeforeAndAfterEach {

  val TEXT_TO_ENCRYPT: String = "testToEncrypt"
  val KEY_ID: String = "keyId"
  val TENANT_ID: Long = 1
  val TEXT_TO_DECRYPT: String = "textToDecrypt"

  private var defaultKmsProviderManager: DefaultEdasProviderManager = _
  private var kmsProvider: PassThroughKmsProvider = _

  override def beforeEach() {
    defaultKmsProviderManager = new DefaultEdasProviderManager(new util.ArrayList[EdasProvider]())
    kmsProvider = new PassThroughKmsProvider()
    defaultKmsProviderManager.registerProvider(kmsProvider)
    super.beforeEach()
  }

  override def afterEach() {
    super.afterEach()
  }

  test("should register provider") {
    assert(defaultKmsProviderManager.hasProvider(kmsProvider.getClass))
  }

  test("should encrypt kms token") {
    val encryptedToken: EdasToken = defaultKmsProviderManager.encrypt(new SimpleEdasToken(KEY_ID, TEXT_TO_ENCRYPT))
    assert(encryptedToken.cipherText().contentEquals(TEXT_TO_ENCRYPT))
  }

  test("should throw provider not found exception when no provider can support a token") {
    defaultKmsProviderManager = new DefaultEdasProviderManager(new util.ArrayList[EdasProvider]())
    assertThrows[EdasProviderNotFoundException]({
      defaultKmsProviderManager.encrypt(new SimpleEdasToken(KEY_ID, TEXT_TO_ENCRYPT))
    })
  }

  test("should decrypt KmsToken") {
    val token = new SimpleEdasToken(KEY_ID, "", TEXT_TO_DECRYPT)
    assert(defaultKmsProviderManager.decrypt(token).plainText().contentEquals(TEXT_TO_DECRYPT))
  }
}
