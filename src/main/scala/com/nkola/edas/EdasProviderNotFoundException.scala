package com.nkola.edas

/**
  * Thrown by the [[DefaultEdasProviderManager]] if no [[EdasProvider]] can handle the
  * encryption/decryption [[EdasToken]] presented.
  *
  * @author Charles Tumwebaze
  */
class EdasProviderNotFoundException(message: String) extends RuntimeException(message) {

}
