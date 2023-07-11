package com.trendyol.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ElementInfo {

  @SerializedName("key")
  @Expose
  private String key;
  @SerializedName("androidValue")
  @Expose
  private String androidValue;
  @SerializedName("androidType")
  @Expose
  private String androidType;

  @SerializedName("androidIndex")
  @Expose
  private int androidIndex;
  @SerializedName("iosValue")
  @Expose
  private String iosValue;
  @SerializedName("iosType")
  @Expose
  private String iosType;
  @SerializedName("iosIndex")
  @Expose
  private int iosIndex;

  @SerializedName("webValue")
  @Expose
  private String webValue;
  @SerializedName("webType")
  @Expose
  private String webType;
  @SerializedName("webIndex")
  @Expose
  private int webIndex;

  public String getKey() {
    return key;
  }

  public String getAndroidValue() {
    return androidValue;
  }

  public String getAndroidType() {
    return androidType;
  }

  public int getAndroidIndex() {
    return androidIndex;
  }

  public String getIosValue() {
    return iosValue;
  }

  public String getIosType() {
    return iosType;
  }

  public int getIosIndex() {
    return iosIndex;
  }

  public String getWebValue() {
    return webValue;
  }

  public String getWebType() {
    return webType;
  }

  public int getWebIndex() {
    return webIndex;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public void setAndroidValue(String androidValue) {
    this.androidValue = androidValue;
  }

  public void setAndroidType(String androidType) {
    this.androidType = androidType;
  }

  public void setAndroidIndex(int androidIndex) {
    this.androidIndex = androidIndex;
  }

  public void setIosValue(String iosValue) {
    this.iosValue = iosValue;
  }

  public void setIosType(String iosType) {
    this.iosType = iosType;
  }

  public void setIosIndex(int iosIndex) {
    this.iosIndex = iosIndex;
  }

  public void setWebValue(String webValue) {
    this.webValue = webValue;
  }

  public void setWebType(String webType) {
    this.webType = webType;
  }

  public void setWebIndex(int webIndex) {
    this.webIndex = webIndex;
  }



  @Override
  public String toString() {
    return "ElementInfo{" +
            "key='" + key + '\'' +
            ", androidValue='" + androidValue + '\'' +
            ", androidType='" + androidType + '\'' +
            ", androidIndex=" + androidIndex +
            ", iosValue='" + iosValue + '\'' +
            ", iosType='" + iosType + '\'' +
            ", iosIndex=" + iosIndex +
            ", webValue='" + webValue + '\'' +
            ", webType='" + webType + '\'' +
            ", webIndex=" + webIndex +
            '}';
  }
}


