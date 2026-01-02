// UdpListener.kt
package com.codaers.f1racehud

import kotlinx.coroutines.*
import java.net.DatagramPacket
import java.net.DatagramSocket
import android.util.Log

class UdpListener {

    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    private var socket: DatagramSocket? = null

    private var packetCount = 0
    private var errorCount = 0

    fun start(onPacket: (Any?) -> Unit) {
        scope.launch {
            try {
                socket = DatagramSocket(UDP_PORT)
                val buffer = ByteArray(BUFFER_SIZE)

                while (isActive && socket?.isClosed == false) {
                    val packet = DatagramPacket(buffer, buffer.size)
                    socket?.receive(packet)

                    val data = packet.data.copyOf(packet.length)
                    packetCount++

                    val parsed: Any? = try {
                        val result = F1Parser.parse(data) // returns Any? for now
                        Log.d("ParserDebug", "Parsed OK: ${result?.let { it::class.simpleName }}")
                        result
                    } catch (e: Exception) {
                        errorCount++
                        Log.e("ParserDebug", "Parse error #$packetCount: ${e.message}", e)
                        null
                    }

                    onPacket(parsed)
                }
            } catch (e: Exception) {
                Log.e("UdpListener", "Socket error: ${e.message}", e)
                onPacket(null)
            }
        }
    }

    fun stop() {
        scope.cancel()
        socket?.close()
        socket = null
    }

    companion object {
        private const val UDP_PORT = 20777
        private const val BUFFER_SIZE = 2048
    }
}
