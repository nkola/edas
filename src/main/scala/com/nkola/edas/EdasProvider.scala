package com.nkola.edas

/**
  * Trait for a Key Management Service Provider that can for encryption/decryption service for a [[EdasToken]]
  * implementation
  *
  * @author Charles Tumwebaze
  */
trait EdasProvider {

  def encrypt(token: EdasToken): EdasToken

  def decrypt(token: EdasToken): EdasToken

  def supports(token: EdasToken): Boolean
}
