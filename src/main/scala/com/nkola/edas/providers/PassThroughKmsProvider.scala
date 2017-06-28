package com.nkola.edas.providers

import com.nkola.edas.{EdasProvider, EdasToken, SimpleEdasToken}

/**
  * PassThroughKmsProvider allows the plainText or cipherText ot pass through without any encryption or decryption
  * process applied to them respectively.
  * <p>
  * In the event where a token is presented for encryption, the value specified in the [[com.nkola.edas.EdasToken#plainText()]] is what
  * is returned in the [[com.nkola.edas.EdasToken#cipherText()]].
  * <p>
  * For a decryption, the value specified in the [[com.nkola.edas.EdasToken#cipherText()]] is what is returned in the [[com.nkola.edas.EdasToken#plainText()]]
  */
class PassThroughKmsProvider extends EdasProvider {
  override def encrypt(token: EdasToken): EdasToken = {
    new SimpleEdasToken(token.keyId(), token.plainText(), token.plainText())
  }

  override def decrypt(token: EdasToken): EdasToken = {
    new SimpleEdasToken(token.keyId(), token.cipherText(), token.cipherText())
  }

  override def supports(token: EdasToken): Boolean = true
}
