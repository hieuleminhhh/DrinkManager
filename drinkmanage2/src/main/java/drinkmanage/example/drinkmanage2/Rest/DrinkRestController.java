package drinkmanage.example.drinkmanage2.Rest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import drinkmanage.example.drinkmanage2.Model.Drink;
import drinkmanage.example.drinkmanage2.Model.FormDataWithFile;
import drinkmanage.example.drinkmanage2.Service.IDrinkService;
import drinkmanage.example.drinkmanage2.Service.IPersonService;

@RestController
@RequestMapping("/restdrink")
public class DrinkRestController {
	@Autowired
	private IDrinkService iDrinkService;
	@Autowired
	private IPersonService iPersonService;
	@Autowired
	private ResourceLoader resourceLoader;

	@GetMapping("/test")
	public ModelAndView test() {

		ModelAndView mav = new ModelAndView("loading");

		return mav;
	}

	@GetMapping("/posing")
	public ModelAndView posing() {
		ModelAndView mav = new ModelAndView("posing");
		return mav;
	}

	@GetMapping
	public List<Drink> listDrink() {
		List<Drink> listPerson = iDrinkService.listDrinkAll();
		return listPerson;
	}
//	@GetMapping
//	public List<Drink> listDrink(@RequestHeader(value = "Accept") String accept,
//			@RequestHeader(value = "User-Agent") String userAgent, @RequestBody String tempt) {
//		System.out.println("Accept _: " + accept);
//		System.out.println("User-Agent _: " + userAgent);
//		System.out.println("tempt _: " + tempt);
//		List<Drink> listPerson = iDrinkService.listDrink();
//		return listPerson;
//	}

	@GetMapping(value = "/requestBody")
	public String listDrink(@RequestParam("fileContent") MultipartFile file, @RequestParam("name") String name) {
		String fileContent;
		if (file.isEmpty()) {
			return "Error: Tệp tin trống.";
		}

		try {
			fileContent = new String(file.getBytes());
			String originalFilename = file.getOriginalFilename();

			String filePath = "/C:/Users/lenovo/Downloads/" + originalFilename;

			file.transferTo(new File(filePath));
			System.out.println(fileContent);
			return "Tệp tin đã được lưu thành công.";
		} catch (IOException e) {
			return "Error: Không thể lưu tệp tin: " + e.getMessage();
		}
	}

	@GetMapping("/upload")
	public ModelAndView upload(Model model, HttpSession session) {
		String filename = (String) session.getAttribute("filename");
		String filePath = "/photo/" + filename;
		System.out.println(filePath);
		ModelAndView mav = new ModelAndView("NewFile");
		mav.addObject("filePath", filePath);
		return mav;
	}

	@PostMapping("/upload")
	public ModelAndView handleFileUpload(@RequestParam("file") MultipartFile file, HttpSession session) {
		try {
			if (file.isEmpty()) {
				return new ModelAndView("error").addObject("message", "Vui lòng chọn một tệp để tải lên.");
			}
			String filename = file.getOriginalFilename();
			File currentDir = new File(".");
			String basePath = currentDir.getCanonicalPath();
			String relativePath = basePath + "/src/main/resources/static/photo/";
			String filePath = relativePath + filename;
			file.transferTo(new File(filePath));

			session.setAttribute("filename", filename);
			System.out.println(filename);
			System.out.println(basePath);
			System.out.println(relativePath);
			System.out.println(filePath);
			return new ModelAndView("redirect:/restdrink/upload");
		} catch (Exception e) {
			return new ModelAndView("error").addObject("message", "Lỗi xử lý tệp tải lên: " + e.getMessage());
		}
	}

	@PostMapping("/api")
	@ResponseBody
	public ResponseEntity<String> addPhoto(@RequestParam("file") MultipartFile file) {
		List<FormDataWithFile> photoPaths = new ArrayList<>();
		try {
			String filename = file.getOriginalFilename();
			File currentDir = new File(".");
			String basePath = currentDir.getCanonicalPath();
			String relativePath = basePath + "/src/main/resources/static/photo/";
			String filePath = relativePath + filename;
			file.transferTo(new File(filePath));

			// String directoryPath = "classpath:static/photo/";
			// Resource resource = resourceLoader.getResource(directoryPath);
			// File directory = resource.getFile();
			// File[] files = directory.listFiles();

			// String fileName = "/photo/" + file.getName();
			// String photoPath = fileSystem.getAbsolutePath();
			FormDataWithFile formData = new FormDataWithFile();
			formData.setFilePath(filePath);
			formData.setFileName(filename);
			System.out.println(filePath);
			System.out.println(filename);
			photoPaths.add(formData);

		} catch (Exception e) {
			e.printStackTrace();
		}
		ObjectMapper objectMapper = new ObjectMapper();
		String json;
		try {
			json = objectMapper.writeValueAsString(photoPaths);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.ok(json);

	}

//	@GetMapping("/api")
//	@ResponseBody
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<String> listPhoto() {
//		String directoryPath = "classpath:static/photo/";
//		List<FormDataWithFile> photoPaths = new ArrayList<>();
//		try {
//			Resource resource = resourceLoader.getResource(directoryPath);
//			File directory = resource.getFile();
//			System.out.println(directory);
//			System.out.println("------------------------------------------------------------");
//			if (directory.exists() && directory.isDirectory()) {
//				File[] files = directory.listFiles();
//				System.out.println(files[1]);
//				System.out.println("------------------------------------------------------------");
//				if (files != null) {
//
//					for (File file : files) {
//						if (file.isFile() && isImageFile(file.getName())) {
//							String fileName = "/photo/" + file.getName();
//							String photoPath = file.getAbsolutePath();
//							FormDataWithFile formData = new FormDataWithFile();
//							formData.setFilePath(photoPath);
//							formData.setFileName(fileName);
//							System.out.println(photoPath);
//							System.out.println(fileName);
//							photoPaths.add(formData);
//						}
//					}
//				}
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		ObjectMapper objectMapper = new ObjectMapper();
//		String json;
//		try {
//			json = objectMapper.writeValueAsString(photoPaths);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//
//		return ResponseEntity.ok(json);
//	}
	// @PostMapping
	// @ResponseBody
	// public ResponseEntity<String> postPhoto(){

	// }
	@GetMapping("/api")
	@ResponseBody
	public ResponseEntity<String> listPhoto() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String username = authentication.getName();
	    String directoryPath = "classpath:static/photo/";
		List<FormDataWithFile> photoPaths = new ArrayList<>();
		try {
			Resource resource = resourceLoader.getResource(directoryPath);
			File directory = resource.getFile();
			System.out.println(directory);
			System.out.println("------------------------------------------------------------");
			if (directory.exists() && directory.isDirectory()) {
				File[] files = directory.listFiles();
				System.out.println(files[1]);
				System.out.println("------------------------------------------------------------");
				if (files != null) {

					for (File file : files) {
						if (file.isFile() && isImageFile(file.getName())) {
							String fileName = "/photo/" + file.getName();
							String photoPath = file.getAbsolutePath();
							FormDataWithFile formData = new FormDataWithFile();
							formData.setFilePath(photoPath);
							formData.setFileName(fileName);
							System.out.println(photoPath);
							System.out.println(fileName);
							photoPaths.add(formData);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		ObjectMapper objectMapper = new ObjectMapper();
		String json;
		try {
			json = objectMapper.writeValueAsString(photoPaths);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	    return ResponseEntity.ok(json);
	}


//	@GetMapping("/api")
//    @ResponseBody
//    public ResponseEntity<String> listPhoto() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String username = authentication.getName();
//        String directoryPath = "classpath:static/photo/";
//        List<FormDataWithFile> photoPaths = new ArrayList<>();
//        try {
//            Resource resource = resourceLoader.getResource(directoryPath);
//            File directory = resource.getFile();
//            System.out.println(directory);
//            System.out.println("------------------------------------------------------------");
//            if (directory.exists() && directory.isDirectory()) {
//                File[] files = directory.listFiles();
//                System.out.println(files[1]);
//                System.out.println("------------------------------------------------------------");
//                if (files != null) {
//                    for (File file : files) {
//                        if (file.isFile() && isImageFile(file.getName())) {
//                            String fileName = "/photo/" + file.getName();
//                            String photoPath = file.getAbsolutePath();
//                            FormDataWithFile formData = new FormDataWithFile();
//                            formData.setFilePath(photoPath);
//                            formData.setFileName(fileName);
//                            System.out.println(photoPath);
//                            System.out.println(fileName);
//                            photoPaths.add(formData);
//                        }
//                    }
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json;
//        try {
//            json = objectMapper.writeValueAsString(photoPaths);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//        // Create HttpHeaders and set the Authorization header
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer eyJVU0VSTkFNRSI6ICJoaWV1bGVtaW5oIiwgIlBBU1NXT1JEIjoiMDIxMDAyIn0=");
//
//        return ResponseEntity.ok().headers(headers).body(json);
//    }
//
//
	private boolean isImageFile(String fileName) {
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png");
	}

	@PostMapping
	public Drink add(@RequestBody Drink drink) {

		return iDrinkService.addDrinkAPI(drink);
	}

	@PutMapping
	public Drink update(@RequestParam("id") long id, @RequestBody Drink drink) {
		return iDrinkService.updateDrink(id, drink);
	}

	@DeleteMapping
	public String delete(@RequestParam("id") long id) {
		boolean delete = iDrinkService.deleteDrink(id);
		if (delete == true) {
			return "Delete suscessfull";
		}
		return "Delete fail";

	}

	@GetMapping("/search")
	public List<Drink> search(@RequestParam("drinkName") String name) {
		return iDrinkService.getDrinkByName(name);
	}

	@GetMapping("/{pageNumber}")
	public List<Drink> listPage(@PathVariable("pageNumber") int currentPage) {
		Page<Drink> page = iDrinkService.listAllPage(currentPage);
		List<Drink> listDrinkPage = page.getContent();
		return listDrinkPage;
	}

	@RequestMapping(value = "/my-api", method = RequestMethod.OPTIONS)
	public ResponseEntity handleOptions() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Allow", "GET, POST, PUT, DELETE");
		return new ResponseEntity(headers, HttpStatus.OK);
	}

}
