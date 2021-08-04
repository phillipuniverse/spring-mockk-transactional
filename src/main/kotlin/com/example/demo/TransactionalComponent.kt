package com.example.demo

import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class TransactionalComponent {

    @Transactional
    fun startTx(): String {
        return doInternal()
    }

    fun doInternal(): String {
        return "original"
    }

}