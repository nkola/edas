package com.nkola.edas

/**
  * API for abstracting the notion of an encryption/decryption service
  * <br>
  * An encryption/decryption request could work as follows.
  *
  * <pre>
  * var session: KmsSession = factory.newSession()
  * var token: KmsToken = session.encrypt(new SimpleKmsToken(keyId, plainText)
  * var encryptedData: String = token.cipherText()
  * </pre>
  *
  * @author Charles Tumwebaze
  */
trait EdasSession {

  /**
    * encrypts the plainText specified in the given token
    *
    * @param token token to use for the encryption request
    * @return token with the cipherText of the encrypted plainText
    */
  def encrypt(token: EdasToken): EdasToken

  /**
    * decrypts the cipherText specified in the given token
    *
    * @param token token to use for the decryption request
    * @return token wih the plainText of the decrypted cipherText
    */
  def decrypt(token: EdasToken): EdasToken
}
