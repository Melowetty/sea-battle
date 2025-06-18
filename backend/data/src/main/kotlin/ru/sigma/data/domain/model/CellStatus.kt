package ru.sigma.data.domain.model

enum class CellStatus {
    HIT,       // Попадание
    MISS,      // Промах
    EMPTY,     // Пустая (не стреляли)
    BORDER     // Граница поля
}