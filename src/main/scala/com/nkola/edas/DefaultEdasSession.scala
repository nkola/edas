package com.nkola.edas

/**
  * Implementation of the [[EdasSession]] trait to provide encryption and decryption services.
  * <br>
  * It delegates the encryption and decryption services to the [[EdasProviderManager]] which
  * will choose the appropriate provider to handle the encryption or decryption request.
  *
  * @author Charles Tumwebaze
  */
class DefaultEdasSession extends EdasSession {
  /**
    * encrypts the plainText specified in the given token
    *
    * @param token token to use for the encryption request
    * @return token with the cipherText of the encrypted plainText
    */
  override def encrypt(token: EdasToken): EdasToken = ???

  /**
    * decrypts the cipherText specified in the given token
    *
    * @param token token to use for the decryption request
    * @return token wih the plainText of the decrypted cipherText
    */
  override def decrypt(token: EdasToken): EdasToken = ???
}
