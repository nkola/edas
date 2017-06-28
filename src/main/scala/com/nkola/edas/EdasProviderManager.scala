package com.nkola.edas

/**
  * Process an encryption/decryption request
  *
  * @author Charles Tumwebaze
  */
trait EdasProviderManager {

  /**
    * performs encryption for the [[EdasToken#plainText()]] specified in the given [[EdasToken]]
    *
    * @param token token whose plain text to encrypt
    * @return [[EdasToken]] with the [[EdasToken#cipherText()]] specified.
    */
  def encrypt(token: EdasToken): EdasToken

  /**
    * performs decryption of the [[EdasToken#cipherText]] specified in the given [[EdasToken]]
    *
    * @param token token whose cipherText to decrypt
    * @return [[EdasToken]] with the plainText of the [[EdasToken#cipherText()]] specified
    */
  def decrypt(token: EdasToken): EdasToken

  /**
    * checks whether a provider with the given class is already registered with the
    * [[EdasProviderManager]]
    *
    * @param providerClass the class of the provider to check
    * @return true of a provider with the given class has been registered and false if non is found.
    */
  def hasProvider(providerClass: Class[_ <: EdasProvider]): Boolean
}
