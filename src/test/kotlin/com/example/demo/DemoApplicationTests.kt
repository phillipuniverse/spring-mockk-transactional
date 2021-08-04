package com.example.demo

import com.ninjasquad.springmockk.SpykBean
import io.mockk.every
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.boot.test.context.SpringBootTest

/**
 * Uncomment the @SpyBean usage and use spyComponent for the tests to pass
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class DemoApplicationTests {

	@SpykBean
	private lateinit var spykComponent: TransactionalComponent

//	@SpyBean
//	private lateinit var spyComponent: TransactionalComponent

	@Test
	@Order(1)
	fun spiedMethod() {
		every { spykComponent.doInternal() } returns "override"
//		Mockito.doReturn("override").`when`(component).doInternal()

		Assertions.assertThat(spykComponent.startTx()).isEqualTo("override")
	}

	@Test
	@Order(2)
	fun realMethod() {
		Assertions.assertThat(spykComponent.startTx()).isEqualTo("original")
	}

}
