package com.dogma.service;

import com.dogma.enums.MachineState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Действия конечного автомата.
 */
public class ParserStateMachineAction {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private char[] document;

    private ContentHandler handler;

    private Queue<ActionState> actionStatesStack = new ArrayDeque<ActionState>();

    public void onAction(final MachineState state, final int indexElement) {
        switch (state) {
            case ELEMENT: {
                registerAction(state, indexElement);
                break;
            }
            case ATTRIBUTE: {
                registerAction(state, indexElement);
                break;
            }
            case VALUE: {
                registerAction(state, indexElement);
                break;
            }
            case END_TAG: {
                endOpenTagAction();
                break;
            }
        }
    }

    private void endOpenTagAction() {

    }

    /**
     * Регистрирует действие автомата.
     *
     * @param machineState состояние автомата.
     * @param indexElement индекс эллемента.
     */
    private void registerAction(final MachineState machineState, final int indexElement) {
        if (actionStatesStack.isEmpty()) {
            actionStatesStack.add(new ActionState(machineState, indexElement));
        } else {
            actionStatesStack.peek().repeatActions();
        }
    }

    /**
     * Вычитывает действия из стека.
     */
    private ActionState getElementActionState() {
        if (!actionStatesStack.isEmpty()) {
            return actionStatesStack.poll();
        } else {
            log.error("element not found");
            return null;
        }
    }

    private class ActionState {
        private MachineState machineState;

        private int startIndex;

        private int length;

        public ActionState(final MachineState machineState, final int startIndex) {
            this.machineState = machineState;
            this.startIndex = startIndex;
            this.length++;
        }

        public void repeatActions() {
            length++;
        }
    }
}
