import com.google.gson.annotations.SerializedName

data class GeoResponse(
    val response: Response?,
)

data class Response(
    @SerializedName("GeoObjectCollection")
    val geoObjectCollection: GeoObjectCollection?,
)

data class GeoObjectCollection(
    val featureMember: List<FeatureMember>?,
    val metaDataProperty: MetaDataPropertyX?,
)

data class FeatureMember(
    @SerializedName("GeoObject")
    val geoObject: GeoObject?,
)

data class MetaDataPropertyX(
    @SerializedName("GeocoderResponseMetaData")
    val geocoderResponseMetaData: GeocoderResponseMetaData?,
)

data class GeoObject(
    @SerializedName("Point")
    val point: Point?,
    val boundedBy: BoundedBy?,
    val description: String?,
    val metaDataProperty: MetaDataProperty?,
    val name: String?,
)

data class Point(
    val pos: String?,
)

data class BoundedBy(
    @SerializedName("Envelope")
    val envelope: Envelope?,
)

data class MetaDataProperty(
    @SerializedName("GeocoderMetaData")
    val geocoderMetaData: GeocoderMetaData?,
)

data class Envelope(
    val lowerCorner: String?,
    val upperCorner: String?,
)

data class GeocoderMetaData(
    @SerializedName("Address")
    val address: Address?,
    @SerializedName("AddressDetails")
    val addressDetails: AddressDetails?,
    val kind: String?,
    val precision: String?,
    val text: String?,
)

data class Address(
    @SerializedName("Components")
    val components: List<Component?>?,
    @SerializedName("country_code")
    val countryCode: String?,
    val formatted: String?,
)

data class AddressDetails(
    @SerializedName("Address")
    val address: String?,
    @SerializedName("Country")
    val country: Country?,
)

data class Component(
    val kind: String?,
    val name: String?,
)

data class Country(
    @SerializedName("AddressLine")
    val addressLine: String?,
    @SerializedName("AdministrativeArea")
    val administrativeArea: AdministrativeArea?,
    @SerializedName("Country")
    val country: CountryX?,
    @SerializedName("CountryName")
    val countryName: String?,
    @SerializedName("CountryNameCode")
    val countryNameCode: String?,
)

data class AdministrativeArea(
    @SerializedName("AdministrativeAreaName")
    val administrativeAreaName: String?,
    @SerializedName("SubAdministrativeArea")
    val subAdministrativeArea: SubAdministrativeArea?,
)

data class CountryX(
    @SerializedName("Locality")
    val locality: Locality?,
)

data class SubAdministrativeArea(
    @SerializedName("SubAdministrativeAreaName")
    val subAdministrativeAreaName: String?,
)

data class Locality(
    @SerializedName("Premise")
    val premise: Premise?,
)

data class Premise(
    @SerializedName("PremiseName")
    val premiseName: String?,
)

data class GeocoderResponseMetaData(
    @SerializedName("Point")
    val point: Point?,
    val found: String?,
    val request: String?,
    val results: String?,
)