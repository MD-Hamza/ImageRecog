package src;

import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;


class Task implements Supplier<HashMap<SpecialImage, String>> {
    private final List<SpecialImage> images;
    private final String command_type;

    private final String category;

    public Task(List<SpecialImage> images, String command_type, String category) {
        this.images = images;
        this.command_type = command_type;
        this.category = category;
    }

    public HashMap<SpecialImage, String> get() {

        HashMap<SpecialImage, String> results = new HashMap<>();

        if (this.command_type.equalsIgnoreCase("classify")) {

            for (SpecialImage img : this.images) {
                ClassifyCommand c = new ClassifyCommand(img.getImg());
                results.put(img, c.getResult());
            }


        }
        else if (this.command_type.equalsIgnoreCase("upload")) {

            for (SpecialImage img : this.images) {
                UploadCommand u = new UploadCommand(img.getImg(), this.category);
                results.put(img, u.getResult());
            }

        }

        return results;


    }
}
