package grails.todo.list

import groovy.time.TimeDuration

class Task {
    String title
    String description
    Date dateTimeStart
    Date dateTimeEnd
    List actions
    static hasMany = [ actions : Action ]

    static constraints = {
        title minSize: 3, unique: true
        description nullable: true
        dateTimeStart valodator: { val, obj -> val < obj.dateTimeEnd }
        dateTimeEnd validator: { val, obj -> val > obj.dateTimeStart }
    }
    static mapping = {
        actions cascade:"all,delete-orphan"
    }
    String toString() {
        title
    }
}
