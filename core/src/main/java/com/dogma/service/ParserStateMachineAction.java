package com.dogma.service;

import com.dogma.enums.MachineState;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Queue;

import lombok.Setter;

/**
 * Действия конечного автомата.
 */
public class ParserStateMachineAction {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Setter
    private char[] document;

    @Setter
    private ContentHandler handler = new ContentHandler();

    private Queue<ActionState> actionStatesStack = new ArrayDeque<ActionState>();

    public void onAction(final MachineState state, final int indexElement) {
        switch (state) {
            case ELEMENT: {
                registerAction(state, indexElement);
                break;
            }
            case END_ELEMENT: {
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
            case END_OPEN_TAG: {
                endOpenTagAction();
                break;
            }
        }
    }

    private void endOpenTagAction() {
        while (!actionStatesStack.isEmpty()) {
            ActionState state = actionStatesStack.poll();
            switch (state.machineState) {
                case ELEMENT: {
                    elementActions(state);
                    break;
                }
                case ATTRIBUTE: {
                    attributeActions(state);
                    break;
                }
                case VALUE: {
                    valueActions(state);
                    break;
                }
                case END_ELEMENT: {
                    endElementActions(state);
                    break;
                }
            }
        }
    }

    private void endElementActions(final ActionState state) {
        handler.endElement("", new String(document, state.startIndex, state.length));
    }

    private void valueActions(final ActionState state) {
        handler.value(new String(document, state.startIndex, state.length));
    }

    private void attributeActions(final ActionState state) {
        handler.attribute("", new String(document, state.startIndex, state.length));
    }

    private void elementActions(final ActionState state) {
        handler.element("", new String(document, state.startIndex, state.length));
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
            this.startIndex = startIndex + 1;
            this.length = 1;
        }

        public void repeatActions() {
            length++;
        }
    }
}
