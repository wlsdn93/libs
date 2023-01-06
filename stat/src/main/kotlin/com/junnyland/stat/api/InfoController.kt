package com.junnyland.stat.api

import com.junnyland.stat.bojClient.ParserBoj
import com.junnyland.stat.service.FindUser
import com.junnyland.stat.svgFixture.bojSvg
import com.junnyland.stat.svgFixture.junnylandSvg
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

interface  InfoController {
    fun info(userId: String): ResponseEntity<String>

    @RestController
    @RequestMapping("/api")
    class InfoWebController(
        private val findUser: FindUser
    ) :InfoController{
        val logger = LoggerFactory.getLogger("boj")!!

        @GetMapping("/info/boj")
        override fun info(@RequestParam userId: String): ResponseEntity<String> {
            if (userId.equals("{{MyId}}")) throw Exception("Please set your id")
            if (userId.contains("}") || userId.contains("{") ) throw Exception("Please set your id")

            logger.info("userId: $userId")
            val call = findUser.findUser(userId)

            return ResponseEntity.ok()
                .header("Content-Type", "image/svg+xml")
                .header("Cache-Control", "no-cache")
                .header("Pragma", "no-cache")
                .header("Expires", "0")
                .header("max-age", "0")
                .header("Access-Control-Allow-Origin", "*")
                .body(bojSvg(call.submit, call.grade, call.solved, call.fail, userId, call.badge));
        }

        @GetMapping("/junnyland")
        fun junnyland(): ResponseEntity<String> {
            return ResponseEntity.ok()
                .header("Content-Type", "image/svg+xml")
                .header("Cache-Control", "no-cache")
                .header("Pragma", "no-cache")
                .header("Expires", "0")
                .header("max-age", "0")
                .header("Access-Control-Allow-Origin", "*")
                .body(junnylandSvg());
        }
    }
}
