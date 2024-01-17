package com.burndown.dto;

import java.util.Set;
import com.burndown.core.entity.Sprint;
import com.burndown.core.entity.Story;


public class SprintBacklogDto {

		private Long id;
		
		private Set<Story> story;
		
		private Sprint sprint;

		public SprintBacklogDto(Sprint sprint) {
			super();
			this.sprint = sprint;
		}

		public SprintBacklogDto(Long id, Set<Story> story, Sprint sprint) {
			super();
			this.id = id;
			this.story = story;
			this.sprint = sprint;
		}

		public SprintBacklogDto(Set<Story> story, Sprint sprint) {
			super();
			this.story = story;
			this.sprint = sprint;
		}

		public Sprint getSprint() {
			return sprint;
		}

		public void setSprint(Sprint sprint) {
			this.sprint = sprint;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Set<Story> getStory() {
			return story;
		}

		public void setStory(Set<Story> story) {
			this.story = story;
		}
		
		
		
}
