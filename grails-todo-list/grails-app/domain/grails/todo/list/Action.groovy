package grails.todo.list

import grails.databinding.BindingFormat

class Action {
    String title
    String description
    @BindingFormat('dd-MM-yy HH:mm')
    Date dateTimeStart
    @BindingFormat('dd-MM-yy HH:mm')
    Date dateTimeEnd
    Task task

    static constraints = {
        task()
        title minSize: 3
        description nullable: true
        dateTimeStart()
        dateTimeEnd validator: { val, obj ->
            def t = obj.task
            val > obj.dateTimeStart && obj.dateTimeStart >= t.dateTimeStart && val <= t.dateTimeEnd
        }
        task valodator: { val, obj ->
            def t = Task.get(val).action
            t.dateTimeStart <= obj.dateTimeEnd && t.dateTimeEnd >= obj.dateTimeStart
        }
    }
    String toString() {
        title
    }
}
