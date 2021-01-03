package com.example.project1

import android.os.Parcel
import android.os.Parcelable

class PhoneBookData(
    val name: String?,
    val number: String?
) : Parcelable, Comparable<PhoneBookData> {
    constructor(source: Parcel) : this(
        source.readString(),
        source.readString()
    )
    override fun describeContents() = 0
    override fun writeToParcel(dest: Parcel?, flags: Int): Unit = with(dest) {
        this?.writeString(name)
        this?.writeString(number)
    }

    companion object CREATOR : Parcelable.Creator<PhoneBookData> {
        override fun createFromParcel(parcel: Parcel): PhoneBookData {
            return PhoneBookData(parcel)
        }

        override fun newArray(size: Int): Array<PhoneBookData?> {
            return arrayOfNulls(size)
        }
    }

    override fun compareTo(other: PhoneBookData): Int {
        return compareValues(this.name, other.name)
    }
}