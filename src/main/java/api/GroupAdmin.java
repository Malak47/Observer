package api;

import observer.Member;
import observer.Sender;
import observer.UndoableStringBuilder;

import java.util.ArrayList;

public class GroupAdmin implements Sender {
    private UndoableStringBuilder sequence;
    private ArrayList<Member> members;

    public GroupAdmin(){
        this.sequence = new UndoableStringBuilder();
        this.members = new ArrayList<Member>();
    }

    public GroupAdmin(UndoableStringBuilder sequence, ArrayList<Member> members){
        this.sequence = sequence;
        this.members = members;
    }

    public GroupAdmin(GroupAdmin other){
        this.sequence = other.sequence;
        this.members = other.members;
    }

    @Override
    public void register(Member obj) {
        this.members.add(obj);
    }

    @Override
    public void unregister(Member obj) {
        this.members.remove(obj);
    }

    @Override
    public void insert(int offset, String obj) {
        this.sequence.insert(offset, obj);
        update();
    }

    @Override
    public void append(String obj) {
        this.sequence.append(obj);
        update();
    }

    @Override
    public void delete(int start, int end) {
        this.sequence.delete(start, end);
        update();
    }

    @Override
    public void undo() {
        this.sequence.undo();
        update();
    }

    private void update(){
        for(Member member:members)
            member.update(this.sequence);
    }
}
