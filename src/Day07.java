import utils.Solvable;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class File {
    String name;
    int size;

    File (String name, int size) {
        this.name = name;
        this.size = size;
    }
}

class Folder {
    String name;
    List<Folder> subfolders;
    List<File> files;

    int size;

    Folder(String name) {
        this.name = name;
        this.subfolders = new ArrayList<>();
        this.files = new ArrayList<>();
        this.size = 0;
    }

    public void prettyPrint(int tabs) {
        for (int i = 0; i < tabs; i++) System.out.print("\t");
        System.out.print(this.name + " (dir)\n");
        for (Folder folder : this.subfolders) {
            folder.prettyPrint(tabs + 1);
        }
        for (File file : this.files) {
            for (int i = 0; i < tabs + 1; i++) System.out.print("\t");
            System.out.print(file.name + " (file, size=" + file.size + ")\n");
        }
    }

    public int setSizes() {
        int sum = 0;
        for (File f : this.files) {
            sum += f.size;
        }
        for (Folder folder : this.subfolders) {
            sum += folder.setSizes();
        }
        this.size = sum;
        return sum;
    }

    public int getSizes() {
        int sum = 0;
        if (this.size <= 100000) sum += this.size;
        for (Folder subfolder: subfolders) {
            sum += subfolder.getSizes();
        }
        return sum;
    }

    public int getDeletionSize(int needfree) {
        int best = Integer.MAX_VALUE;
        for (Folder f : this.subfolders) {
            int size = f.getDeletionSize(needfree);
            if (size >= needfree && size < best) best = size;
        }
        if (this.size < best && this.size >= needfree) best = this.size;
        return best;
    }
}

class Command {
    String name;
    List<String> output;

    Command(String name) {
        this.name = name;
        this.output = new ArrayList<>();
    }
}

public class Day07 implements Solvable<Integer> {
    public Integer part1(List<String> input) {
        List<Command> commands = parseCommands(input);
        Stack<Folder> pwd = new Stack<>();
        Folder root = new Folder("/");
        pwd.push(root);

        for (Command cmd : commands) {
            Folder dir = pwd.peek();
            if (cmd.name.equals("ls")) {
                for (String output : cmd.output) {
                    String[] out = output.split(" ");
                    if (out[0].equals("dir")) {
                        dir.subfolders.add(new Folder(out[1]));
                    } else {
                        dir.files.add(new File(out[1], Integer.parseInt(out[0])));
                    }
                }
            } else {
                String folderName = cmd.name.split(" ")[1].strip();
                if (folderName.equals("..")) pwd.pop();
                else pwd.push(findSubfolder(dir.subfolders, folderName));
            }
        }

        root.prettyPrint(0);

        root.setSizes();
        int size = root.getSizes();

        return size;
    }

    public Integer part2(List<String> input) {
        List<Command> commands = parseCommands(input);
        Stack<Folder> pwd = new Stack<>();
        Folder root = new Folder("/");
        pwd.push(root);

        for (Command cmd : commands) {
            Folder dir = pwd.peek();
            if (cmd.name.equals("ls")) {
                for (String output : cmd.output) {
                    String[] out = output.split(" ");
                    if (out[0].equals("dir")) {
                        dir.subfolders.add(new Folder(out[1]));
                    } else {
                        dir.files.add(new File(out[1], Integer.parseInt(out[0])));
                    }
                }
            } else {
                String folderName = cmd.name.split(" ")[1].strip();
                if (folderName.equals("..")) pwd.pop();
                else pwd.push(findSubfolder(dir.subfolders, folderName));
            }
        }

        root.prettyPrint(0);

        root.setSizes();
        int needFree = 30000000 - (70000000 - root.size);
        int size = root.getDeletionSize(needFree);

        return size;
    }

    private Folder findSubfolder(List<Folder> folders, String name) {
        for (Folder folder : folders) {
            if (folder.name.equals(name)) return folder;
        }
        System.out.println("Subfolder not found");
        return null;
    }

    private List<Command> parseCommands(List<String> input) {
        List<Command> commands = new ArrayList<>();

        Command activeCommand = null;
        for (String line : input) {
            if (line.startsWith("$")) {
                if (activeCommand != null) commands.add(activeCommand);
                Command cmd = new Command(line.substring(2).strip());
                activeCommand = cmd;
            } else {
                activeCommand.output.add(line.strip());
            }
        }
        if (activeCommand != null) commands.add(activeCommand);
        return commands;
    }
}
