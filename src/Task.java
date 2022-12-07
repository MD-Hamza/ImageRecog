package src;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;


class Task implements Supplier<HashMap<SpecialImage, String>> {
    private final List<SpecialImage> images;
    private final String command_type;
    private final String category;
    private final String model;

    public Task(List<SpecialImage> images, String command_type, String category, String model) {
        this.images = images;
        this.command_type = command_type;
        this.category = category;
        this.model = model;
    }

    /**
     * Allows each Thread to send commands depending on command type
     * @return  HashMap<SpecialImage, String>
     * @throws RuntimeException
     */
    public HashMap<SpecialImage, String> get() {

        HashMap<SpecialImage, String> results = new HashMap<>();

        if (this.command_type.equalsIgnoreCase("classify")) {

            for (SpecialImage img : this.images) {
                ClassifyCommand c = null;
                try {
                    c = new ClassifyCommand(img.getImg(), this.model);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                results.put(img, c.receiver.getResult());
            }


        }
        else if (this.command_type.equalsIgnoreCase("upload")) {

            for (SpecialImage img : this.images) {
                UploadCommand u = null;
                try {
                    u = new UploadCommand(img.getImg(), this.category);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                results.put(img, u.receiver.getResult());
            }

        }

        return results;


    }
}
