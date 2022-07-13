package com.HSYCO.mostafa;

import com.HSYCO.mostafa.http_request.RequestHttp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class VlcController {
    private static boolean doLoop;
    private static final Logger logger = LoggerFactory.getLogger(VlcController.class);

    public static void main(String[] args) {
        String command =
                """
                                        
                        1 => play video
                        2 => pause video
                        3 => stop video
                        4 => info video
                        others => exit video
                        ----------------------
                        """;
        Scanner input = new Scanner(System.in);
        logger.info(command);
        do {
            switch (input.nextInt()) {
                case 1 -> RequestHttp.playVideo();
                case 2 -> RequestHttp.pauseVideo();
                case 3 -> RequestHttp.stopVideo();
                case 4 -> RequestHttp.getInfo();
                default -> doLoop = true;
            }
        } while (!doLoop);
    }
}
