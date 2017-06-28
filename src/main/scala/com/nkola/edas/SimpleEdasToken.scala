package com.nkola.edas

/**
  * Basic Implementation of the [[EdasToken]]
  *
  * @author Charles Tumwebaze
  */
class SimpleEdasToken(private val id: String = "", private val plain: String = "") extends EdasToken {
  private var cipher: String = ""

  def this(id: String, plain: String, cipher: String) {
    this(id, plain)
    this.cipher = cipher
  }

  /**
    * unique identifier of the key used for encrypting the [[EdasToken#plainText()]]
    */
  override def keyId(): String = id

  /**
    * Represents data to be encrypted in the case of an encryption request or data that <br/>
    * has been decrypted in the case of a decryption request.
    */
  override def plainText(): String = plain

  /**
    * The encrypted [[EdasToken#plainText()]] in the case of an encryption request.
    *
    * @return
    */
  override def cipherText(): String = cipher
}
