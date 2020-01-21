package com.bebound.sample.generated;

import com.bebound.sdk.Message;
import com.bebound.sdk.PushListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.lang.Boolean;
import java.lang.Override;
import java.lang.String;

public final class BoolRequest extends Message<BoolRequest.Data> {
    public BoolRequest() {
        super();
        data = new Data();
    }

    @Override
    public String manifest() {
        return getJsonManifest().toString();

    }

    public BoolRequest withBoolTest(Boolean bool_test) {
        data.bool_test = bool_test;
        return this;
    }

    @Override
    public String parameters() {
        return data.getAll();
    }

    JsonObject getJsonManifest() {
        JsonObject attrs, message = new JsonObject();
        message.addProperty("name", "bool_request");
        message.addProperty("id", 0);
        JsonArray params = new JsonArray();

        JsonObject _boolTest_ = new JsonObject();
        _boolTest_.addProperty("name", "bool_test");
        _boolTest_.addProperty("optional", false);
        _boolTest_.addProperty("type", "boolean");
        params.add(_boolTest_);

        message.add("params", params);
        return message;
    }

    @Override
    public byte id() {
        return 0;
    }

    @Override
    public Message fromJson(String json) {
        JsonObject data = new Gson().fromJson(json, JsonObject.class);

        BoolRequest _boolRequest_ = new BoolRequest();
        _boolRequest_.withBoolTest(data.get("bool_test").getAsBoolean());

        return _boolRequest_;
    }

    public static class Data {
        public Boolean bool_test;

        JsonObject getJsonValues() {
            JsonObject json = new JsonObject();

            json.addProperty("bool_test", bool_test);

            return json;
        }

        private String getAll() {
            return getJsonValues().toString();
        }
    }

    public abstract static class Listener implements PushListener<BoolRequest> {
        @Override
        public final int id() {
            return 0;
        }

        @Override
        public final Class<? extends BoolRequest> getClazz() {
            return BoolRequest.class;
        }

        @Override
        public byte[] decrypt(byte[] payload) {
            return payload;
        }

        @Override
        public boolean verify(byte[] payload, byte[] signature) {
            return true;
        }
    }
}
