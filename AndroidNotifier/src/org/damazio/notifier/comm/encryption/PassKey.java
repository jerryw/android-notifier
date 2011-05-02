package org.damazio.notifier.comm.encryption;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.spec.SecretKeySpec;

public class PassKey {
  static final String HASH_ALGORITHM = "MD5";
  static final String ENCRYPTION_KEY_TYPE = "AES";

  private final byte[] keyBytes;
  private final byte[] iv;
  private final SecretKeySpec keySpec;

  public PassKey(String passPhrase, int numHashes) {
    // Use an MD5 to generate an arbitrary initialization vector
    keyBytes = passPhraseToKey(passPhrase, HASH_ALGORITHM, numHashes);
    iv = doDigest(keyBytes, "MD5");
    keySpec = new SecretKeySpec(keyBytes, ENCRYPTION_KEY_TYPE);
  }

  public byte[] getKeyBytes() {
    return keyBytes;
  }

  public SecretKeySpec getKeySpec() {
    return keySpec;
  }

  public byte[] getInitVector() {
    return iv;
  }

  /**
   * Converts a user-entered pass phrase into a hashed binary value which is
   * used as the encryption key.
   */
  private byte[] passPhraseToKey(String passphrase, String hashAlgorithm, int numHashes) {
    if (numHashes < 1) {
      throw new IllegalArgumentException("Need a positive hash count");
    }

    byte[] passPhraseBytes;
    try {
      passPhraseBytes = passphrase.getBytes("UTF8");
    } catch (UnsupportedEncodingException e) {
      throw new IllegalArgumentException("Bad passphrase encoding", e);
    }

    // Hash it multiple times to keep the paranoid people happy :)
    byte[] keyBytes = passPhraseBytes;
    for (int i = 0; i < numHashes; i++) {
      keyBytes = doDigest(keyBytes, hashAlgorithm);
    }

    return keyBytes;
  }


  private byte[] doDigest(byte[] data, String algorithm) {
    try {
      MessageDigest md = MessageDigest.getInstance(algorithm);
      md.update(data);
      return md.digest();
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalArgumentException("Algorithm not available", e);
    }
  }
}
