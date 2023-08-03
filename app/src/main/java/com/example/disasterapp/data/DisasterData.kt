package com.example.disasterapp.data

import com.squareup.moshi.Json

data class DisasterData (
    val statusCode: Int,
    val result: DataResult
)

data class DataResult (
    val type: String,
    val objects:DataObjects,
    val arcs: List<Double>,
    val bbox: List<Double>
)

data class DataObjects (
    val output: DataOutput
)

data class DataOutput (
    val type: String,
    val geometries: List<DataGeometries>
)

data class DataGeometries (
    val type: String,
    val properties: DataProperties,
    val coordinates: List<Double>
)

data class DataProperties (
    val pkey: String,
    @Json(name = "created_at")
    val createdAt: String,
    val source: String,
    val status: String,
    val url: String,
    @Json(name = "image_url")
    val imageUrl: String?,
    @Json(name = "disaster_type")
    val disasterType: String,
    val reportData: DataReport?,
    val tags: DataTags,
    val title: String?,
    val text: String
)

data class DataReport (
    @Json(name = "report_type")
    val reportType: String,
    @Json(name = "flood_depth")
    val floodDepth: Int
)

data class DataTags (
    @Json(name = "district_id")
    val districtId: String?,
    @Json(name = "region_code")
    val regionCode: String,
    @Json(name = "local_area_id")
    val localAreaId: String?,
    @Json(name = "instance_region_code")
    val instanceRegionCode: String
)