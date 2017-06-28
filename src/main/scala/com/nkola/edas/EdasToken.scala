package com.nkola.edas

/**
  * Represents a token for an encryption/decryption request
  *
  * @author Charles Tumwebaze
  */
trait EdasToken {

  /**
    * unique identifier of the key used for encrypting the {@link #plainText}
    */
  def keyId(): String

  /**
    * Represents data to be encrypted in the case of an encryption request or data that <br/>
    * has been decrypted in the case of a decryption request.
    */
  def plainText(): String

  /**
    * The encrypted [[EdasToken#plainText()]] in the case of an encryption request.
    *
    * @return
    */
  def cipherText(): String
}
