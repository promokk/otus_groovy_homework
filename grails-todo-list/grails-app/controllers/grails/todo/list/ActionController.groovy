package grails.todo.list

import grails.rest.*
import grails.converters.*
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ActionController extends RestfulController {

    static responseFormats = ['json', 'xml']

    ActionController() {
        super(Action)
    }

//    ActionService actionService
//
//    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
//
//    def index(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
//        respond actionService.list(params), model:[actionCount: actionService.count()]
//    }
//
//    def show(Long id) {
//        respond actionService.get(id)
//    }
//
//    def create() {
//        respond new Action(params)
//    }
//
//    def save(Action action) {
//        if (action == null) {
//            notFound()
//            return
//        }
//
//        try {
//            actionService.save(action)
//        } catch (ValidationException e) {
//            respond action.errors, view:'create'
//            return
//        }
//
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'action.label', default: 'Action'), action.id])
//                redirect action
//            }
//            '*' { respond action, [status: CREATED] }
//        }
//    }
//
//    def edit(Long id) {
//        respond actionService.get(id)
//    }
//
//    def update(Action action) {
//        if (action == null) {
//            notFound()
//            return
//        }
//
//        try {
//            actionService.save(action)
//        } catch (ValidationException e) {
//            respond action.errors, view:'edit'
//            return
//        }
//
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.updated.message', args: [message(code: 'action.label', default: 'Action'), action.id])
//                redirect action
//            }
//            '*'{ respond action, [status: OK] }
//        }
//    }
//
//    def delete(Long id) {
//        if (id == null) {
//            notFound()
//            return
//        }
//
//        actionService.delete(id)
//
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.deleted.message', args: [message(code: 'action.label', default: 'Action'), id])
//                redirect action:"index", method:"GET"
//            }
//            '*'{ render status: NO_CONTENT }
//        }
//    }
//
//    protected void notFound() {
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.not.found.message', args: [message(code: 'action.label', default: 'Action'), params.id])
//                redirect action: "index", method: "GET"
//            }
//            '*'{ render status: NOT_FOUND }
//        }
//    }
}
