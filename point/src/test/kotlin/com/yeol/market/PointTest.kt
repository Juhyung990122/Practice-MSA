//package com.coffeeshop.ordersystem.point.domain
//
//import com.yeol.market.
//
//common.exception.InvalidPriceException
//import com.yeol.market.point.domain.Point
//import io.kotest.assertions.throwables.shouldThrow
//import io.kotest.core.spec.style.DescribeSpec
//import io.kotest.matchers.shouldBe
//
//class PointTest : DescribeSpec() {
//    init {
//        describe(".charge") {
//            context("포인트를 충전할때") {
//                val point = com.yeol.market.point.domain.Point(1L, 0)
//                it("1000원을 충전하면 포인트가 충전된다") {
//                    point.charge(1000)
//                    point.memberId shouldBe 1L
//                    point.balance shouldBe 1000
//                }
//
//                it("충전 금액이 음수라면 예외가 발생한다.") {
//                    shouldThrow<com.yeol.market.common.exception.InvalidPriceException> { point.charge(-1000L) }
//                }
//            }
//        }
//    }
//}
