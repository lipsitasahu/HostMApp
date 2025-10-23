package com.example.hostmapp

import android.os.Parcel
import android.os.Parcelable

data class ParcelableDashboardData(
    val speed: Float,
    val rpm: Float,
    val fuelLevel: Float,
    val batteryLevel: Float,
    val gear: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readString() ?: "N"
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeFloat(speed)
        parcel.writeFloat(rpm)
        parcel.writeFloat(fuelLevel)
        parcel.writeFloat(batteryLevel)
        parcel.writeString(gear)
    }

    override fun toString(): String {
        return "Speed: $speed, RPM: $rpm, Fuel Level: $fuelLevel, Battery Level: $batteryLevel"
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<ParcelableDashboardData> {
        override fun createFromParcel(parcel: Parcel): ParcelableDashboardData =
            ParcelableDashboardData(parcel)
        override fun newArray(size: Int): Array<ParcelableDashboardData?> = arrayOfNulls(size)
    }
}
