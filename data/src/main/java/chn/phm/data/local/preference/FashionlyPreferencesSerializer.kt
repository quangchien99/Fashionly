package chn.phm.data.local.preference

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import chn.phm.data.FashionlyPreferences
import java.io.InputStream
import java.io.OutputStream

object FashionlyPreferencesSerializer : Serializer<FashionlyPreferences> {

    override val defaultValue: FashionlyPreferences = FashionlyPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): FashionlyPreferences {
        try {
            return FashionlyPreferences.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: FashionlyPreferences, output: OutputStream) = t.writeTo(output)
}
