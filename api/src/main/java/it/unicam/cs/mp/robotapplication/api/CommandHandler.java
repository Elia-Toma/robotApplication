package it.unicam.cs.mp.robotapplication.api;

import it.unicam.cs.mp.robotapplication.api.instructions.*;
import it.unicam.cs.mp.robotapplication.utilities.FollowMeParserHandler;

/**
 * This class implements the {@link FollowMeParserHandler} interface and handles the commands
 * parsed by the parser.
 */
public class CommandHandler implements FollowMeParserHandler {
    private final Program program;

    public CommandHandler(Program program) {
        this.program = program;
    }

    @Override
    public void parsingStarted() {
        this.program.clearInstructionQueue();
    }

    @Override
    public void parsingDone() {
    }

    @Override
    public void moveCommand(double[] args) {
        MoveInstruction movement = new MoveInstruction(args);

        this.program.addInstruction(movement);
    }

    @Override
    public void moveRandomCommand(double[] args) {
        MoveRandomInstruction randomMovement = new MoveRandomInstruction(args);

        this.program.addInstruction(randomMovement);
    }

    @Override
    public void signalCommand(String label) {
        SignalInstruction signal = new SignalInstruction(label);

        this.program.addInstruction(signal);
    }

    @Override
    public void unsignalCommand(String label) {
        UnsignalInstruction unsignal = new UnsignalInstruction(label);

        this.program.addInstruction(unsignal);
    }

    @Override
    public void followCommand(String label, double[] args) {
        FollowInstruction follow = new FollowInstruction(label, args);

        this.program.addInstruction(follow);
    }

    @Override
    public void stopCommand() {
        StopInstruction stop = new StopInstruction();

        this.program.addInstruction(stop);
    }

    @Override
    public void continueCommand(int s) {
        for (int i = 0; i < s; i++)
            this.program.addInstruction(new ContinueInstruction(s));
    }

    @Override
    public void repeatCommandStart(int n) {
        this.program.addInstruction(new RepeatInstruction(n));
    }

    @Override
    public void untilCommandStart(String label) {
        this.program.addInstruction(new UntilInstruction(label));
    }

    @Override
    public void doForeverStart() {
        this.program.addInstruction(new DoForeverInstruction());
    }

    @Override
    public void doneCommand() {
        this.program.addInstruction(new DoneInstruction());
    }
}
