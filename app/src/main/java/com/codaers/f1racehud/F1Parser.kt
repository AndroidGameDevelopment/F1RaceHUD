package com.codaers.f1racehud

import java.nio.ByteBuffer
import java.nio.ByteOrder
import android.util.Log

import com.codaers.f1racehud.f1.game23.F1Parser23
import com.codaers.f1racehud.f1.game24.F1Parser24
import com.codaers.f1racehud.f1.game25.F1Parser25

object F1Parser {

    fun parse(bytes: ByteArray): Any? {
        if (bytes.size < 29) return "Packet too small (${bytes.size} bytes)"

        // Wrap the incoming UDP packet into a ByteBuffer
        val buffer = ByteBuffer.wrap(bytes).order(ByteOrder.LITTLE_ENDIAN)

        // Parse the header (this consumes 29 bytes)
        val header = parseHeader(buffer)

        // Store detected game version globally
        GlobalState.gameVersion.value = header.packetFormat.toInt()

        // Debug log of the parsed fields
        Log.d(
            "ParserDebug",
            "GameVersion=${header.packetFormat}, packetVersion=${header.packetVersion}, " +
                    "packetId=${header.packetId}, playerCarIndex=${header.playerCarIndex}"
        )

        // Raw header bytes for debugging
        val rawHeader = bytes.copyOfRange(0, 29)
        println("Raw header bytes: ${rawHeader.joinToString(",")}")

        // Skip packets not in the active set
        if (header.packetId.toInt() !in GlobalState.activePacketIDs.value) {
            Log.d("F1Parser", "Skipping packetId=${header.packetId}, not in active set")
            return null
        }

        // Dispatch to correct version parser
        return when (header.packetFormat.toInt()) {
            2023 -> F1Parser23.parse(buffer, header)
            2024 -> F1Parser24.parse(buffer, header)
            2025 -> F1Parser25.parse(buffer, header)
            else -> {
                Log.w("F1Parser", "Unsupported packetFormat=${header.packetFormat}")
                null
            }
        }
    }

    private fun parseHeader(buffer: ByteBuffer): PacketHeader {
        buffer.order(ByteOrder.LITTLE_ENDIAN)
        return PacketHeader(
            packetFormat = buffer.short.toUShort(),
            gameYear = buffer.get().toUByte(),
            gameMajorVersion = buffer.get().toUByte(),
            gameMinorVersion = buffer.get().toUByte(),
            packetVersion = buffer.get().toUByte(),
            packetId = buffer.get().toUByte(),
            sessionUID = buffer.long.toULong(),
            sessionTime = buffer.float,
            frameIdentifier = buffer.int.toUInt(),
            overallFrameIdentifier = buffer.int.toUInt(),
            playerCarIndex = buffer.get().toUByte(),
            secondaryPlayerCarIndex = buffer.get().toUByte()
        )
    }
}

// Header is version‑agnostic → belongs here
data class PacketHeader(
    val packetFormat: UShort,            // 2 bytes - 2023, 2024, 2025
    val gameYear: UByte,                 // 1 byte  - Game year - last two digits e.g. 25
    val gameMajorVersion: UByte,         // 1 byte  - Game major version - "X.00"
    val gameMinorVersion: UByte,         // 1 byte  - Game minor version - "1.XX"
    val packetVersion: UByte,            // 1 byte  - Version of this packet type, all start from 1
    val packetId: UByte,                 // 1 byte  - Identifier for the packet type, see below
    val sessionUID: ULong,               // 8 bytes - Unique identifier for the session
    val sessionTime: Float,              // 4 bytes - Session timestamp
    val frameIdentifier: UInt,           // 4 bytes - Identifier for the frame the data was retrieved on
    val overallFrameIdentifier: UInt,    // 4 bytes - Overall identifier for the frame the data was retrieved on, doesn't go back after flashbacks
    val playerCarIndex: UByte,           // 1 byte  - Index of player's car in the array
    val secondaryPlayerCarIndex: UByte   // 1 byte  - Index of secondary player's car in the array (split screen) 255 if no second player
)