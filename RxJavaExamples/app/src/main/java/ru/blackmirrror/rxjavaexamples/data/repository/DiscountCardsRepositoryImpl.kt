package ru.blackmirrror.rxjavaexamples.data.repository

import io.reactivex.rxjava3.core.Observable
import ru.blackmirrror.rxjavaexamples.data.models.DiscountCardDTO
import ru.blackmirrror.rxjavaexamples.domain.models.DiscountCard
import ru.blackmirrror.rxjavaexamples.domain.repository.DiscountCardsRepository

class DiscountCardsRepositoryImpl : DiscountCardsRepository {
    override fun getDiscountCards(show: Boolean): Observable<List<DiscountCard>> {
        val firstSource = getCardsStorageFirst()
            .onErrorReturn { emptyList() }

        val secondSource = getCardsStorageSecond()
            .onErrorReturn { emptyList() }

        return Observable.zip(firstSource, secondSource) { firstList, secondList ->
            (if (show && (firstList.isEmpty() || secondList.isEmpty())) {
                emptyList<List<DiscountCard>>()
            } else {
                mergeDiscountCards(firstList, secondList)
            }) as List<DiscountCard>
        }
    }

    private fun getCardsStorageFirst(): Observable<List<DiscountCardDTO>> {
        return Observable.create {
            it.onNext(
                listOf(
                    DiscountCardDTO(1, "Nike"),
                    DiscountCardDTO(2, "Adidas"),
                    DiscountCardDTO(3, "Reebok"),
                    DiscountCardDTO(4, "Puma"),
                    DiscountCardDTO(5, "Saucony")
                )
            )
            it.onComplete()
        }
    }

    private fun getCardsStorageSecond(): Observable<List<DiscountCardDTO>> {
        return Observable.create {
            it.onError(IllegalStateException())
            it.onNext(
                listOf(
                    DiscountCardDTO(1, "Puma"),
                    DiscountCardDTO(2, "Hoka"),
                    DiscountCardDTO(3, "Li-Ning")
                )
            )
        }
    }

    private fun mergeDiscountCards(
        firstList: List<DiscountCardDTO>,
        secondList: List<DiscountCardDTO>
    ): List<DiscountCard> {
        val mergedMap = mutableMapOf<String, DiscountCard>()

        firstList.forEach { card ->
            mergedMap[card.shop] = DiscountCard(card.id, card.shop)
        }

        secondList.forEach { card ->
            if (mergedMap.containsKey(card.shop)) {
                return@forEach
            } else {
                mergedMap[card.shop] = DiscountCard(generateNewId(), card.shop)
            }
        }

        return mergedMap.values.toList()
    }

    private fun generateNewId(): Int {
        return (1..1000).random()
    }
}