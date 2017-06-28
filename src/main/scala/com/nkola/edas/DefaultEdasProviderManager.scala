package com.nkola.edas

import java.util

import scala.collection.JavaConverters._

/**
  * Iterates the [[EdasToken]] through a list of [[EdasProvider]]s.
  * <p>
  * The providers will be tried in the order in which they are specified. If a provider
  * indicates that it cannot support the given token, the next provider in the list will be probed
  * until a provider can handle the encryption/decryption token.
  * <br>
  * If no provider can support the presented [[EdasToken]], a [[EdasProviderNotFoundException]]
  * will be thrown
  *
  * @author Charles Tumwebaze
  */
class DefaultEdasProviderManager extends EdasProviderManager {

  private var kmsProviders: util.List[EdasProvider] = new util.ArrayList[EdasProvider]()

  def this(kmsProviders: util.List[EdasProvider]) {
    this()
    this.kmsProviders = kmsProviders
  }

  /**
    * performs encryption for the [[EdasToken#plainText()]] specified in the given [[EdasToken]]
    *
    * @param token token whose plain text to encrypt
    * @return [[EdasToken]] with the [[EdasToken#cipherText()]] specified.
    */
  override def encrypt(token: EdasToken): EdasToken = {
    kmsProviders.asScala.find(p => p.supports(token))
      .getOrElse(throw new EdasProviderNotFoundException("Provider not found"))
      .encrypt(token)
  }

  /**
    * performs decryption of the [[EdasToken#cipherText]] specified in the given [[EdasToken]]
    *
    * @param token token whose cipherText to decrypt
    * @return [[EdasToken]] with the plainText of the [[EdasToken#cipherText()]] specified
    */
  override def decrypt(token: EdasToken): EdasToken = {
    kmsProviders.asScala.find(p => p.supports(token))
      .getOrElse(throw new EdasProviderNotFoundException("Provider not found"))
      .decrypt(token)
  }

  /**
    * checks whether a provider with the given class is already registered with the
    * [[EdasProviderManager]]
    *
    * @param providerClass the class of the provider to check
    * @return true of a provider with the given class has been registered and false if non is found.
    */
  override def hasProvider(providerClass: Class[_ <: EdasProvider]): Boolean = {
    kmsProviders.asScala.count(p => p.getClass.isAssignableFrom(providerClass)) > 0
  }

  /**
    * adds the given [[EdasProvider]] to the end of the list of providers
    *
    * @param kmsProvider the provider to register
    */
  def registerProvider(kmsProvider: EdasProvider): Unit = {
    kmsProviders.add(kmsProvider)
  }
}
