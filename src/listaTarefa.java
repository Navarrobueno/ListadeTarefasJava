import java.io.*;
import java.util.*;

public class listaTarefa {

    private static final String FILENAME = "tarefa.txt";

    public static void main(String[] args) {
        List<String> tasks = new ArrayList<>();
        loadTasksFromFile(tasks);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nLista de Tarefas:");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Marcar Tarefa como Concluída");
            System.out.println("3. Listar Tarefas Pendentes");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Digite a tarefa a ser adicionada: ");
                    String task = scanner.nextLine();
                    tasks.add(task);
                    saveTasksToFile(tasks);
                    System.out.println("Tarefa adicionada com sucesso!");
                    break;
                case 2:
                    if (!tasks.isEmpty()) {
                        System.out.println("Tarefas pendentes:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                        System.out.print("Digite o número da tarefa concluída: ");
                        int completedTask = scanner.nextInt();
                        if (completedTask >= 1 && completedTask <= tasks.size()) {
                            tasks.remove(completedTask - 1);
                            saveTasksToFile(tasks);
                            System.out.println("Tarefa marcada como concluída!");
                        } else {
                            System.out.println("Número de tarefa inválido.");
                        }
                    } else {
                        System.out.println("Nenhuma tarefa pendente.");
                    }
                    break;
                case 3:
                    if (!tasks.isEmpty()) {
                        System.out.println("Tarefas pendentes:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                    } else {
                        System.out.println("Nenhuma tarefa pendente.");
                    }
                    break;
                case 4:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void loadTasksFromFile(List<String> tasks) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
        } catch (IOException e) {
            // Arquivo não encontrado ou erro de leitura
        }
    }

    private static void saveTasksToFile(List<String> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar as tarefas no arquivo.");
        }
    }
}