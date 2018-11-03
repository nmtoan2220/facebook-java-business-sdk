/**
 * Copyright (c) 2015-present, Facebook, Inc. All rights reserved.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 * use, copy, modify, and distribute this software in source code or binary
 * form for use in connection with the web services and APIs provided by
 * Facebook.
 *
 * As with any software that integrates with the Facebook platform, your use
 * of this software is subject to the Facebook Developer Principles and
 * Policies [http://developers.facebook.com/policy/]. This copyright notice
 * shall be included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 */

package com.facebook.ads.sdk;

import java.io.File;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import com.facebook.ads.sdk.APIException.MalformedResponseException;

/**
 * This class is auto-generated.
 *
 * For any issues or feature requests related to this class, please let us know
 * on github and we'll fix in our codegen framework. We'll not be able to accept
 * pull request for this class.
 *
 */
public class AdRuleFilters extends APINode {
  @SerializedName("field")
  private String mField = null;
  @SerializedName("operator")
  private EnumOperator mOperator = null;
  @SerializedName("value")
  private Object mValue = null;
  @SerializedName("id")
  private String mId = null;
  protected static Gson gson = null;

  public AdRuleFilters() {
  }

  public String getId() {
    return getFieldId().toString();
  }
  public static AdRuleFilters loadJSON(String json, APIContext context) {
    AdRuleFilters adRuleFilters = getGson().fromJson(json, AdRuleFilters.class);
    if (context.isDebug()) {
      JsonParser parser = new JsonParser();
      JsonElement o1 = parser.parse(json);
      JsonElement o2 = parser.parse(adRuleFilters.toString());
      if (o1.getAsJsonObject().get("__fb_trace_id__") != null) {
        o2.getAsJsonObject().add("__fb_trace_id__", o1.getAsJsonObject().get("__fb_trace_id__"));
      }
      if (!o1.equals(o2)) {
        context.log("[Warning] When parsing response, object is not consistent with JSON:");
        context.log("[JSON]" + o1);
        context.log("[Object]" + o2);
      };
    }
    adRuleFilters.context = context;
    adRuleFilters.rawValue = json;
    return adRuleFilters;
  }

  public static APINodeList<AdRuleFilters> parseResponse(String json, APIContext context, APIRequest request) throws MalformedResponseException {
    APINodeList<AdRuleFilters> adRuleFilterss = new APINodeList<AdRuleFilters>(request, json);
    JsonArray arr;
    JsonObject obj;
    JsonParser parser = new JsonParser();
    Exception exception = null;
    try{
      JsonElement result = parser.parse(json);
      if (result.isJsonArray()) {
        // First, check if it's a pure JSON Array
        arr = result.getAsJsonArray();
        for (int i = 0; i < arr.size(); i++) {
          adRuleFilterss.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context));
        };
        return adRuleFilterss;
      } else if (result.isJsonObject()) {
        obj = result.getAsJsonObject();
        if (obj.has("data")) {
          if (obj.has("paging")) {
            JsonObject paging = obj.get("paging").getAsJsonObject();
            if (paging.has("cursors")) {
                JsonObject cursors = paging.get("cursors").getAsJsonObject();
                String before = cursors.has("before") ? cursors.get("before").getAsString() : null;
                String after = cursors.has("after") ? cursors.get("after").getAsString() : null;
                adRuleFilterss.setCursors(before, after);
            }
            String previous = paging.has("previous") ? paging.get("previous").getAsString() : null;
            String next = paging.has("next") ? paging.get("next").getAsString() : null;
            adRuleFilterss.setPaging(previous, next);
            if (context.hasAppSecret()) {
              adRuleFilterss.setAppSecret(context.getAppSecretProof());
            }
          }
          if (obj.get("data").isJsonArray()) {
            // Second, check if it's a JSON array with "data"
            arr = obj.get("data").getAsJsonArray();
            for (int i = 0; i < arr.size(); i++) {
              adRuleFilterss.add(loadJSON(arr.get(i).getAsJsonObject().toString(), context));
            };
          } else if (obj.get("data").isJsonObject()) {
            // Third, check if it's a JSON object with "data"
            obj = obj.get("data").getAsJsonObject();
            boolean isRedownload = false;
            for (String s : new String[]{"campaigns", "adsets", "ads"}) {
              if (obj.has(s)) {
                isRedownload = true;
                obj = obj.getAsJsonObject(s);
                for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
                  adRuleFilterss.add(loadJSON(entry.getValue().toString(), context));
                }
                break;
              }
            }
            if (!isRedownload) {
              adRuleFilterss.add(loadJSON(obj.toString(), context));
            }
          }
          return adRuleFilterss;
        } else if (obj.has("images")) {
          // Fourth, check if it's a map of image objects
          obj = obj.get("images").getAsJsonObject();
          for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
              adRuleFilterss.add(loadJSON(entry.getValue().toString(), context));
          }
          return adRuleFilterss;
        } else {
          // Fifth, check if it's an array of objects indexed by id
          boolean isIdIndexedArray = true;
          for (Map.Entry entry : obj.entrySet()) {
            String key = (String) entry.getKey();
            if (key.equals("__fb_trace_id__")) {
              continue;
            }
            JsonElement value = (JsonElement) entry.getValue();
            if (
              value != null &&
              value.isJsonObject() &&
              value.getAsJsonObject().has("id") &&
              value.getAsJsonObject().get("id") != null &&
              value.getAsJsonObject().get("id").getAsString().equals(key)
            ) {
              adRuleFilterss.add(loadJSON(value.toString(), context));
            } else {
              isIdIndexedArray = false;
              break;
            }
          }
          if (isIdIndexedArray) {
            return adRuleFilterss;
          }

          // Sixth, check if it's pure JsonObject
          adRuleFilterss.clear();
          adRuleFilterss.add(loadJSON(json, context));
          return adRuleFilterss;
        }
      }
    } catch (Exception e) {
      exception = e;
    }
    throw new MalformedResponseException(
      "Invalid response string: " + json,
      exception
    );
  }

  @Override
  public APIContext getContext() {
    return context;
  }

  @Override
  public void setContext(APIContext context) {
    this.context = context;
  }

  @Override
  public String toString() {
    return getGson().toJson(this);
  }


  public String getFieldField() {
    return mField;
  }

  public AdRuleFilters setFieldField(String value) {
    this.mField = value;
    return this;
  }

  public EnumOperator getFieldOperator() {
    return mOperator;
  }

  public AdRuleFilters setFieldOperator(EnumOperator value) {
    this.mOperator = value;
    return this;
  }

  public Object getFieldValue() {
    return mValue;
  }

  public AdRuleFilters setFieldValue(Object value) {
    this.mValue = value;
    return this;
  }

  public String getFieldId() {
    return mId;
  }

  public AdRuleFilters setFieldId(String value) {
    this.mId = value;
    return this;
  }



  public static enum EnumOperator {
      @SerializedName("GREATER_THAN")
      VALUE_GREATER_THAN("GREATER_THAN"),
      @SerializedName("LESS_THAN")
      VALUE_LESS_THAN("LESS_THAN"),
      @SerializedName("EQUAL")
      VALUE_EQUAL("EQUAL"),
      @SerializedName("NOT_EQUAL")
      VALUE_NOT_EQUAL("NOT_EQUAL"),
      @SerializedName("IN_RANGE")
      VALUE_IN_RANGE("IN_RANGE"),
      @SerializedName("NOT_IN_RANGE")
      VALUE_NOT_IN_RANGE("NOT_IN_RANGE"),
      @SerializedName("IN")
      VALUE_IN("IN"),
      @SerializedName("NOT_IN")
      VALUE_NOT_IN("NOT_IN"),
      @SerializedName("CONTAIN")
      VALUE_CONTAIN("CONTAIN"),
      @SerializedName("NOT_CONTAIN")
      VALUE_NOT_CONTAIN("NOT_CONTAIN"),
      @SerializedName("ANY")
      VALUE_ANY("ANY"),
      @SerializedName("ALL")
      VALUE_ALL("ALL"),
      @SerializedName("NONE")
      VALUE_NONE("NONE"),
      NULL(null);

      private String value;

      private EnumOperator(String value) {
        this.value = value;
      }

      @Override
      public String toString() {
        return value;
      }
  }


  synchronized /*package*/ static Gson getGson() {
    if (gson != null) {
      return gson;
    } else {
      gson = new GsonBuilder()
        .excludeFieldsWithModifiers(Modifier.STATIC)
        .excludeFieldsWithModifiers(Modifier.PROTECTED)
        .disableHtmlEscaping()
        .create();
    }
    return gson;
  }

  public AdRuleFilters copyFrom(AdRuleFilters instance) {
    this.mField = instance.mField;
    this.mOperator = instance.mOperator;
    this.mValue = instance.mValue;
    this.mId = instance.mId;
    this.context = instance.context;
    this.rawValue = instance.rawValue;
    return this;
  }

  public static APIRequest.ResponseParser<AdRuleFilters> getParser() {
    return new APIRequest.ResponseParser<AdRuleFilters>() {
      public APINodeList<AdRuleFilters> parseResponse(String response, APIContext context, APIRequest<AdRuleFilters> request) throws MalformedResponseException {
        return AdRuleFilters.parseResponse(response, context, request);
      }
    };
  }
}
