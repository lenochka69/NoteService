interface CrudService<E> {
    abstract val text: String
    abstract val title: String

    fun add(entity: E): Note
    fun delete(id: Int):Boolean
    fun edit(entity: E): Boolean
    fun getById(id: Int): E
    fun restore(id: Int):Boolean

}