package com.nkola.edas.providers

import com.nkola.edas.{EdasToken, SimpleEdasToken}
import org.scalatest.{BeforeAndAfterEach, FunSpec}

/**
  * This test uses the FunSpec style for writing tests
  */
class PassThroughEdasProviderTest extends FunSpec with BeforeAndAfterEach {
  val TEXT_TO_ENCRYPT = "textToEncrypt"
  val KEY_ID = "keyId"
  val TEXT_TO_DECRYPT = "textToDecrypt"

  private var passThroughKmsProvider: PassThroughKmsProvider = _

  override protected def beforeEach(): Unit = {
    passThroughKmsProvider = new PassThroughKmsProvider()
  }

  describe("Testing") {
    it("should perform a pass through encryption") {
      val token = new SimpleEdasToken(KEY_ID, TEXT_TO_ENCRYPT)
      assert(passThroughKmsProvider.encrypt(token).cipherText().contentEquals(TEXT_TO_ENCRYPT))
    }

    it("should perform a pass through decryption") {
      val token = new SimpleEdasToken(KEY_ID, "", TEXT_TO_DECRYPT)
      assert(passThroughKmsProvider.decrypt(token).plainText().contentEquals(TEXT_TO_DECRYPT))
    }

    it("should support all kms tokens") {
      val token = new EdasToken {
        /**
          * unique identifier of the key used for encrypting the {@link #plainText}
          */
        override def keyId(): String = KEY_ID

        /**
          * Represents data to be encrypted in the case of an encryption request or data that <br/>
          * has been decrypted in the case of a decryption request.
          */
        override def plainText(): String = TEXT_TO_ENCRYPT

        /**
          * The encrypted [[EdasToken#plainText()]] in the case of an encryption request.
          *
          * @return
          */
        override def cipherText(): String = TEXT_TO_DECRYPT
      }

      assert(passThroughKmsProvider.supports(token))
    }
  }
}
