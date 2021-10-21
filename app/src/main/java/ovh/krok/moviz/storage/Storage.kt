package ovh.krok.moviz.storage

interface Storage<T> {
    fun insert(obj: T)
    fun find(id: Int) : T?
    fun findAll() : List<T>
    fun update(id: Int, obj: T)
    fun delete(id: Int)
    fun size() : Int
}